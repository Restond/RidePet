# RidePet

RidePet 是一个基于 Paper 1.12.2 的 Minecraft 坐骑宠物插件，提供马匹坐骑的召唤、收回、死亡复活、属性加成、战斗限制和 GUI 管理等功能。

> 本项目采用 [GNU GPL v3.0](LICENSE) 开源协议

## 功能特性

- **坐骑蛋物品** — 通过坐骑蛋获取和切换坐骑
- **坐骑列表 GUI** — 可视化管理所有坐骑，实时刷新状态
- **战斗系统** — 战斗中自动收回坐骑，脱战后方可重新召唤
- **死亡与复活** — 坐骑死亡后需等待复活时间，期间无法召唤
- **属性加成** — 可选对接 SX-Attribute 插件，骑乘时提供属性加成（仅骑乘生效，下骑自动移除）
- **数据持久化** — 玩家数据自动保存，离线自动收回坐骑
- **重命名系统** — 在 GUI 中通过 Shift+右键重命名坐骑
- **跨世界收回** — 玩家切换世界时自动收回坐骑
- **内存清理** — 定期清理离线玩家内存数据

## 命令

| 命令 | 说明 | 权限 |
|------|------|------|
| `/ridepet reload` | 重载配置与玩家数据 | `ridepet.admin` |
| `/ridepet give <玩家> <类型ID> [等级]` | 给予玩家坐骑蛋 | `ridepet.admin` |
| `/ridepet list` | 列出所有坐骑类型 | `ridepet.admin` |
| `/ridepet gui` | 打开坐骑列表 | `ridepet.use` |

命令支持 Tab 补全。

## 权限

| 权限节点 | 说明 | 默认 |
|----------|------|------|
| `ridepet.use` | 允许使用坐骑 | 所有人 |
| `ridepet.admin` | 管理员权限 | OP |

## 坐骑蛋操作

手持坐骑蛋时：

- **左键空中** — 召唤/收回坐骑
- **右键** — 打开坐骑列表 GUI

首次使用某种坐骑蛋时会消耗蛋并添加坐骑到列表，后续使用相同类型和等级的蛋时切换该坐骑的召唤/收回状态。

## 坐骑列表 GUI

在坐骑列表界面中：

- **左键** — 召唤/收回坐骑
- **右键** — 删除坐骑
- **Shift + 右键** — 重命名坐骑（在聊天框输入新名称，输入 `cancel` 取消）

GUI 会实时刷新坐骑状态（召唤中/休息中/死亡复活倒计时）。

## 属性系统

属性仅在骑乘时生效：

1. **骑乘坐骑** → 应用属性加成到玩家
2. **下骑坐骑** → 移除属性加成
3. **收回坐骑** → 无条件移除属性加成（即使未骑乘也安全调用）
4. **坐骑死亡** → 自动移除骑乘者属性
5. **战斗强制收回** → 移除属性加成

属性通过 SX-Attribute 的 Lore 解析机制实现，坐骑配置中的 `attributes` 字段会被解析为 SX-Attribute 属性数据。

## 配置文件

### config.yml

```yaml
settings:
  language: "zh_CN"
  debug: false
  combat_timeout: 10        # 战斗状态持续秒数

egg:
  cooldown: 3000            # 坐骑操作冷却时间（毫秒）
  max_pets: 3               # 每位玩家最大坐骑数量

attribute_priority:
  - "sx"                    # 属性插件优先级，支持 sx（SX-Attribute）

messages:
  prefix: "§6[坐骑] §r"
  pet_summoned: "§a宠物已召唤!"
  pet_removed: "§c宠物已收回!"
  pet_dead: "§c你的宠物死亡了! 需要 {time} 秒复活"
  cooldown: "§c请等待 {time} 秒后再试!"
  pet_reviving: "§c宠物正在复活中，还需 {time} 秒!"
  in_combat: "§c你正处于战斗状态，无法召唤坐骑!"
  combat_cooldown: "§c还需 {time} 秒脱离战斗后才能召唤坐骑!"
```

### 坐骑类型配置

坐骑类型配置位于 `plugins/RidePet/pets/` 目录下，每个 `.yml` 文件定义一种坐骑：

```yaml
id: "default_horse"            # 唯一标识
name: "§f普通马"               # 显示名称

egg:
  material: "MONSTER_EGG"      # 蛋物品材质
  display_name: "§f[坐骑蛋] 普通马"
  lore:
    - "§7右键查看详情"

horse:
  color: "BROWN"               # 马匹颜色
  style: "NONE"                # 马匹花纹
  max_health: 20.0             # 最大生命值

levels:
  1:
    attributes: ["物理攻击 +2", "移动速度 +5%"]  # 属性 Lore（SX-Attribute 解析）
    revive_time: 30            # 死亡复活时间（秒）
    horse_speed: 0.2           # 马匹移动速度
    horse_jump: 0.7            # 马匹跳跃力
  2:
    attributes: ["物理攻击 +4", "移动速度 +8%"]
    revive_time: 60
    horse_speed: 0.25
    horse_jump: 0.8
```

马匹颜色可选值：`BROWN`, `WHITE`, `BLACK`, `DARK_BROWN`, `CHESTNUT`, `GRAY`, `CREAMY`

马匹花纹可选值：`NONE`, `WHITE`, `WHITEFIELD`, `WHITE_DOTS`, `BLACK_DOTS`

## 依赖

| 插件 | 类型 | 说明 |
|------|------|------|
| SX-Attribute | 可选 | 提供属性加成功能，未安装则坐骑不提供属性加成 |

## 数据存储

玩家坐骑数据以 YAML 格式存储在 `plugins/RidePet/data/` 目录下，每个玩家一个文件（以 UUID 命名）。插件每 24 小时自动保存一次数据，玩家退出时也会自动保存并收回坐骑。

### 重载说明

`/ridepet reload` 会执行以下操作：

1. 保存所有在线玩家数据到磁盘
2. 重载配置文件（`config.yml` + `pets/` 目录）
3. 清理所有在线玩家的坐骑实体
4. 清空内存中的玩家数据
5. 从磁盘重新加载玩家数据

这使得管理员可以在后台修改或删除玩家数据文件后，通过重载使修改生效。

## 构建

```bash
./gradlew build
```

构建产物位于 `build/libs/` 目录。

## 项目结构

```
src/main/java/com/restond/ridepet/
├── RidePet.java              # 主类，命令处理
├── attribute/
│   ├── AttributeBridge.java  # 属性桥接接口
│   └── SXAttributeBridge.java # SX-Attribute 实现
├── gui/
│   └── PetListGUI.java       # 坐骑列表 GUI
├── listener/
│   ├── ChatListener.java     # 重命名聊天监听
│   ├── CombatListener.java   # 战斗状态监听
│   ├── GUIListener.java      # GUI 点击监听
│   ├── PetDeathListener.java # 坐骑死亡监听
│   ├── PlayerInteractListener.java # 坐骑蛋交互监听
│   ├── PlayerQuitListener.java     # 玩家退出监听
│   ├── VehicleListener.java  # 骑乘/下骑监听
│   └── WorldChangeListener.java   # 切换世界监听
├── manager/
│   ├── ConfigManager.java    # 配置管理
│   ├── DataManager.java      # 数据持久化
│   └── PetManager.java       # 坐骑核心管理
├── pet/
│   ├── PetData.java          # 坐骑实例数据
│   └── PetType.java          # 坐骑类型定义
└── util/
    ├── ItemBuilder.java      # 物品构建工具
    └── MessageUtil.java      # 消息工具
```
