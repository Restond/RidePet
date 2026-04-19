# RidePet 更新日志

## v1.1.3 — 2026/04/19

### 新功能

- **新增删除坐骑命令**：`/ridepet delete <类型ID>`，玩家可删除自己拥有的指定类型坐骑。删除时若坐骑处于召唤状态会先收回。需要 `ridepet.use` 权限。
  - `RidePet.java` 新增 `handleDelete()` 方法，命令 switch 和 Tab 补全均已适配。

### 功能调整

- **同类型坐骑自动升级替换**：使用不同等级的同类型坐骑蛋时，不再创建重复坐骑，而是自动替换为高等级坐骑。例如玩家已拥有 1 级马，再使用 2 级马蛋会自动升级为 2 级马，旧的 1 级坐骑会被收回并移除。
  - `PlayerInteractListener.handleTogglePet()` 中匹配逻辑从"同类型且同等级"改为"同类型"；新增 `matchedPet.getLevel() != eggLevel` 分支处理升级替换。

### Bug 修复

- **修复过期坐骑用新蛋续期失败**：当玩家持有已过期的 PetData，再次获取同 ID 的新蛋（未过期）并左键使用时，`handleTogglePet` 匹配到旧 PetData 后直接尝试召唤，因 `isExpired()` 仍为 true 而失败。
  - 在 `handleTogglePet` 中，当 `matchedPet.isExpired()` 时判断蛋是否过期：若蛋未过期，从蛋的 Lore 解析新到期时间，更新 PetData 的 `acquireTime` 和 `expireMillis` 实现续期；若蛋也过期，提示并返回。

### 涉及文件

| 文件 | 变更内容 |
|------|---------|
| `RidePet.java` | 新增 `handleDelete()` 方法；命令 switch 增加 `delete` 分支；Tab 补全增加 delete 参数；帮助信息增加 delete 条目 |
| `PlayerInteractListener.java` | `handleTogglePet()` 中增加过期 PetData 续期逻辑；匹配逻辑改为同类型匹配，新增不同等级自动升级替换分支 |

## v1.1.2 — 2026/04/14

### Bug 修复

- **修复坐骑蛋类型编号自动添加逻辑误判**：`createPetEgg()` 中使用 `line.contains(petType.getId())` 判断 Lore 是否已包含类型信息，过于宽泛。当 Lore 描述中包含坐骑 id 字符串时（如赤兔的"马中赤兔"包含"赤兔"），误判为已有类型编号行，导致跳过添加 `[RidePet] 类型编号:` 行，坐骑蛋无法被 `isPetEgg()` 识别。
  - 将判断条件从 `line.contains(petType.getId())` 改为 `line.contains("[RidePet] 类型编号:")`，精确匹配类型编号格式行。

### 涉及文件

| 文件 | 变更内容 |
|------|---------|
| `RidePet.java` | `createPetEgg()` 中类型编号判断条件从 `contains(petType.getId())` 改为 `contains("[RidePet] 类型编号:")` |


## v1.1.1 — 2026/04/14

### Bug 修复

- **修复坐骑蛋过期判断逻辑错误**：`createPetEgg` 写入的是格式化日期字符串（如 `2026/04/14 12:30:00`），但 `getAcquireTimeFromEgg` 使用 `Long.parseLong()` 解析 → `NumberFormatException` → 返回 -1 → `isEggExpired` 中 `acquireTime <= 0` 判断为 `true` → 返回 `false`，导致已过期的蛋仍可使用。
  - 新增 `getExpireTimeFromEgg()` 方法，使用 `SimpleDateFormat.parse()` 解析 `[RidePet] 到期时间:` 行，直接比较到期时间戳与当前时间。
  - 删除旧的 `getAcquireTimeFromEgg()` 方法。
  - `isEggExpired()` 改为调用 `getExpireTimeFromEgg()` 而非 `getAcquireTimeFromEgg()`。

- **修复已召唤坐骑自动回收失效**：旧数据中 `acquire_time=0, expire_millis=0`，`PetData.isExpired()` 中 `expireMillis <= 0` 直接返回 `false`，导致坐骑永远不会过期。
  - `PetManager.updatePetDataFromConfig()` 新增旧数据迁移逻辑：当 `petData.getExpireMillis() <= 0` 且 `petType.getExpireMillis() > 0` 时，从配置同步 `expireMillis`；若 `acquireTime` 也为 0，则设为当前时间。
  - `RidePet.startExpireCheckTask()` 中同样增加迁移逻辑，确保在线玩家旧数据也能被修正。
  - 为 `startExpireCheckTask` 添加 per-player try-catch，防止单个玩家异常导致整个定时任务终止。

### 功能调整

- **坐骑蛋 Lore 自动添加项精简**：`createPetEgg()` 不再自动添加"获取时间戳"和"获取时间"，仅自动添加"到期时间"（仅当 `petType.getExpireMillis() > 0` 时）。
  - 到期时间通过 `System.currentTimeMillis() + petType.getExpireMillis()` 计算后格式化写入 Lore。

### 涉及文件

| 文件 | 变更内容 |
|------|---------|
| `RidePet.java` | `createPetEgg()` 精简 Lore、仅写到期时间；`startExpireCheckTask()` 增加旧数据迁移 + per-player 异常捕获 |
| `PlayerInteractListener.java` | 新增 `getExpireTimeFromEgg()`，`isEggExpired()` 改用到期时间判断，删除 `getAcquireTimeFromEgg()` |
| `PetManager.java` | `updatePetDataFromConfig()` 增加旧数据 `expireMillis`/`acquireTime` 迁移逻辑 |
| `PetData.java` | 无逻辑变更（调试日志已移除） |
