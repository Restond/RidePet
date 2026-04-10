# RidePet 使用文档

RidePet 是一个基于 Paper 1.12.2 的 Minecraft 坐骑插件，提供马匹坐骑的召唤、收回、死亡复活、属性加成、战斗限制和 GUI 管理等功能。

> 本项目采用 [GNU GPL v3.0](LICENSE) 开源协议

## 功能特性 

- 坐骑蛋物品 — 通过坐骑蛋获取和切换坐骑
- 坐骑列表 GUI — 可视化管理所有坐骑
- 战斗系统 — 战斗中自动收回坐骑，脱战后方可重新召唤
- 死亡与复活 — 坐骑死亡后需等待复活时间
- 属性加成 — 可选对接 SX-Attribute 插件，骑乘时提供属性加成
- 数据持久化 — 玩家数据自动保存，离线自动收回坐骑

## 命令

| 命令 | 说明 | 权限 |
|------|------|------|
| `/ridepet reload` | 重载配置文件 | `ridepet.admin` |
| `/ridepet give <玩家> <类型ID> [等级]` | 给予玩家坐骑蛋 | `ridepet.admin` |
| `/ridepet list` | 列出所有坐骑类型 | `ridepet.admin` |

## 权限

| 权限节点 | 说明 | 默认 |
|----------|------|------|
| `ridepet.use` | 允许使用坐骑 | 所有人 |
| `ridepet.admin` | 管理员权限 | OP |

## 坐骑蛋操作

手持坐骑蛋时：

- **左键空中** — 召唤/收回坐骑
- **右键** — 打开坐骑列表 GUI

## 坐骑列表 GUI

在坐骑列表界面中：

- **左键** — 召唤/收回坐骑
- **右键** — 删除坐骑
- **Shift + 右键** — 重命名坐骑（在聊天框输入新名称，输入 `cancel` 取消）

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
    attributes: ["物理攻击 +2", "移动速度 +5%"]  # 属性 Lore
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
