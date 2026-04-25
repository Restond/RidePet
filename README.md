# RidePet

RidePet 是一个基于 Paper 1.12.2 的 Minecraft 坐骑宠物插件。玩家通过坐骑蛋收放和管理坐骑，支持属性加成、等级系统、使用期限、战斗限制等功能。

> 本项目采用 [GNU GPL v3.0](LICENSE) 开源协议

## 目录

- [功能特性](#功能特性)
- [安装](#安装)
- [依赖](#依赖)
- [命令](#命令)
- [权限](#权限)
- [使用方法](#使用方法)
- [使用期限](#使用期限)
- [属性系统](#属性系统)
- [配置文件](#配置文件)
- [数据存储](#数据存储)
- [构建](#构建)
- [项目结构](#项目结构)

## 功能特性

**坐骑管理**
- 通过坐骑蛋物品召唤 / 收回坐骑，蛋不消耗，永久保留
- 同类型同等级蛋：切换召唤 / 收回状态
- 同类型不同等级蛋：自动升级替换为高等级坐骑
- 每位玩家可拥有多个坐骑（可配置上限）

**等级系统**
- 每个坐骑类型支持多等级配置，不同等级可有不同的属性加成、移动速度、跳跃力、复活时间

**使用期限**
- 可配置坐骑蛋使用期限（如 `7d`、`3h30m`），到期后蛋无法使用
- 到期蛋自动销毁，已召唤的坐骑自动收回
- 到期坐骑可使用未过期蛋续期

**战斗系统**
- 战斗中自动收回坐骑，脱战后才能重新召唤
- 脱战冷却时间可配置

**死亡与复活**
- 坐骑死亡后进入复活倒计时，期间无法召唤
- 复活时间按等级可配置

**属性加成**
- 可选对接 SX-Attribute 插件，骑乘时提供属性加成
- 仅骑乘生效，下骑自动移除
- 属性插件优先级可配置

**安全保护**
- 危险区域（岩浆、固体方块）自动阻止召唤
- 冷却时间防止频繁操作

**数据持久化**
- 玩家坐骑数据以 YAML 格式存储，每个玩家独立文件
- 玩家退出 / 切换世界时自动收回坐骑并保存
- 定期自动保存 + 定期检查过期坐骑

**配置管理**
- 启动时自动补全缺失的配置项
- 支持运行时热重载配置文件

## 安装

1. 将 `RidePet.jar` 放入服务器的 `plugins/` 目录
2. 重启服务器（或使用插件管理器加载）
3. 插件启动后会在 `plugins/RidePet/` 下生成默认配置文件：
   - `config.yml` — 主配置文件
   - `pets/` — 坐骑类型配置目录
4. 根据需要编辑配置文件，使用 `/ridepet reload` 热重载

## 依赖

| 插件 | 类型 | 说明 |
|------|------|------|
| [SX-Attribute](https://www.mcbbs.net/thread-849463-1-1.html) | 可选 | 提供属性加成功能，未安装时坐骑仅无属性加成 |

## 命令

| 命令 | 说明 | 权限 |
|------|------|------|
| `/ridepet reload` | 重载配置与玩家数据 | `ridepet.admin` |
| `/ridepet give <玩家> <类型ID> [等级]` | 给予玩家坐骑蛋 | `ridepet.admin` |
| `/ridepet list` | 列出所有坐骑类型 | `ridepet.admin` |
| `/ridepet delete <类型ID>` | 删除自己的指定类型坐骑 | `ridepet.use` |

所有命令支持 Tab 补全。

## 权限

| 权限节点 | 说明 | 默认 |
|----------|------|------|
| `ridepet.use` | 允许使用坐骑 | 所有人 |
| `ridepet.admin` | 管理员权限 | OP |

## 使用方法

**获取坐骑蛋**：管理员通过 `/ridepet give <玩家> <类型ID> [等级]` 发放。

**操作坐骑**：手持坐骑蛋，左键空气即可召唤 / 收回坐骑。坐骑蛋不会消耗，可作为永久工具保留。

**坐骑升级**：如果玩家已拥有类型 A 等级 1 的坐骑，使用类型 A 等级 2 的蛋时，旧坐骑会被自动收回并替换为高等级坐骑。

**删除坐骑**：玩家可使用 `/ridepet delete <类型ID>` 删除自己拥有的指定类型坐骑。已召唤的坐骑会先被收回再删除。

### 坐骑蛋识别

坐骑蛋使用 `MONSTER_EGG` 材质，通过 Lore 中的隐藏标识（`[RidePet] 类型编号:`）识别类型。因此：
- 不要手动修改坐骑蛋的 Lore，否则可能导致无法识别
- 不同服务器之间不可直接转移坐骑蛋（除非目标服务器有相同 ID 的坐骑类型配置）

## 使用期限

在坐骑类型配置的 `options.duration` 中可设置蛋的使用期限，格式为 `数值+单位`，支持组合：

| 单位 | 含义 | 示例 |
|------|------|------|
| `d` | 天 | `7d` = 7天 |
| `h` | 小时 | `3h` = 3小时 |
| `m` | 分钟 | `30m` = 30分钟 |

组合示例：`1d12h30m` = 1天12小时30分钟

获取坐骑蛋时，蛋的 Lore 会自动写入到期时间。到期后左键使用时会自动销毁蛋并收回已召唤的坐骑。留空或不填表示无期限。

> 注意：过期只影响蛋的使用，不影响已召唤的坐骑——直到定期检查任务发现并移除过期坐骑为止。

如果坐骑已过期，使用同类型的未过期蛋可以续期——蛋的新到期时间会更新到坐骑数据中。

## 属性系统

属性仅在骑乘时生效：

1. **骑乘坐骑** → 应用属性加成到玩家
2. **下骑坐骑** → 移除属性加成
3. **收回坐骑** → 移除属性加成
4. **坐骑死亡** → 移除骑乘者属性
5. **战斗强制收回** → 移除属性加成

属性通过 SX-Attribute 的 Lore 解析机制实现。坐骑配置中的 `attributes` 字段会被解析为 SX-Attribute 属性数据。此外，插件会将马的速度同步到玩家的移动速度（取两者较高值）。

`attribute_priority` 配置项控制属性插件匹配顺序，默认 `["sx"]`，可扩展支持其他属性插件。

## 配置文件

### config.yml

```yaml
settings:
  debug: false
  combat_timeout: 10        # 战斗状态持续秒数

egg:
  cooldown: 3000            # 坐骑操作冷却时间（毫秒）
  max_pets: 3               # 每位玩家最大坐骑数量

attribute_priority:
  - "sx"                    # 属性插件优先级

messages:
  prefix: "§6[坐骑] §r"
  pet_summoned: "§a宠物已召唤!"
  pet_removed: "§c宠物已收回!"
  pet_dead: "§c你的宠物死亡了! 需要 {time} 秒复活"
  cooldown: "§c请等待 {time} 秒后再试!"
  no_permission: "§c你没有权限执行此操作!"
  pet_full: "§c你已拥有最大数量的坐骑!"
  pet_reviving: "§c宠物正在复活中，还需 {time} 秒!"
  pet_renamed: "§a宠物已重命名为: {name}"
  pet_deleted: "§c坐骑已删除!"
  in_combat: "§c你正处于战斗状态，无法召唤坐骑!"
  combat_cooldown: "§c还需 {time} 秒脱离战斗后才能召唤坐骑!"
```

### 坐骑类型配置

坐骑类型配置位于 `plugins/RidePet/pets/` 目录下，每个 `.yml` 文件定义一种坐骑：

```yaml
id: "default_horse"            # 唯一标识
name: "§f普通马"               # 显示名称
description:                   # 描述文本（可选，显示在蛋的 Lore 中）
  - "§7一只普通的马匹坐骑"

egg:
  material: "MONSTER_EGG"      # 蛋的材质（可选，默认 MONSTER_EGG）
  display_name: "§f[坐骑蛋] 普通马"
  lore:
    - "§7左键收放坐骑"

horse:
  color: "BROWN"               # 马匹颜色
  style: "NONE"                # 马匹花纹
  max_health: 20.0             # 最大生命值

options:
  # 使用期限，格式: 1d3h5m（天/小时/分钟），留空表示无期限
  duration: ""

levels:
  1:
    attributes:                # 属性 Lore（SX-Attribute 解析）
      - "物理攻击 +2"
      - "移动速度 +5%"
    revive_time: 30            # 死亡复活时间（秒）
    horse_speed: 0.2           # 马匹移动速度
    horse_jump: 0.7            # 马匹跳跃力
  2:
    attributes:
      - "物理攻击 +4"
      - "移动速度 +8%"
    revive_time: 60
    horse_speed: 0.25
    horse_jump: 0.8
```

**马匹颜色可选值**：`BROWN`, `WHITE`, `BLACK`, `DARK_BROWN`, `CHESTNUT`, `GRAY`, `CREAMY`

**马匹花纹可选值**：`NONE`, `WHITE`, `WHITEFIELD`, `WHITE_DOTS`, `BLACK_DOTS`

## 数据存储

玩家坐骑数据以 YAML 格式存储在 `plugins/RidePet/data/` 目录下，每个玩家一个文件（以 UUID 命名）。

**保存时机**：
- 每 5 分钟自动保存（异步）
- 玩家退出时自动保存并收回坐骑
- 玩家切换世界时自动收回坐骑
- 插件关闭时保存所有数据
- 重载配置时保存所有数据

**过期检查**：
- 每 10 秒检查在线玩家的坐骑是否过期
- 过期坐骑自动移除并收回

### 重载说明

`/ridepet reload` 会执行以下操作：

1. 保存所有在线玩家数据到磁盘
2. 重载配置文件（`config.yml` + `pets/` 目录）
3. 收回所有在线玩家的坐骑实体
4. 清空内存中的玩家数据
5. 从磁盘重新加载玩家数据
6. 检查并移除过期坐骑

这使得管理员可以在后台修改或删除玩家数据文件后，通过重载使修改生效。

## 构建

```bash
./gradlew build
```

构建产物位于 `build/libs/` 目录。

要求：JDK 8+，Gradle。

## 项目结构

```
src/main/java/com/restond/ridepet/
├── RidePet.java                   # 主类，命令处理
├── attribute/
│   ├── AttributeBridge.java       # 属性桥接接口
│   └── SXAttributeBridge.java     # SX-Attribute 实现
├── listener/
│   ├── CombatListener.java        # 战斗状态监听
│   ├── PetDeathListener.java      # 坐骑死亡监听
│   ├── PlayerInteractListener.java # 坐骑蛋交互监听
│   ├── PlayerQuitListener.java    # 玩家退出监听
│   ├── VehicleListener.java       # 骑乘 / 下骑监听
│   └── WorldChangeListener.java   # 切换世界监听
├── manager/
│   ├── ConfigManager.java         # 配置管理
│   ├── DataManager.java           # 数据持久化
│   └── PetManager.java            # 坐骑核心管理
├── pet/
│   ├── PetData.java               # 坐骑实例数据
│   └── PetType.java               # 坐骑类型定义
└── util/
    ├── ItemBuilder.java           # 物品构建工具
    └── MessageUtil.java           # 消息工具
```
