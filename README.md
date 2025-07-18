# 🧩 BetonQuestAddon

> 📌 *An addon for integrating [BetonQuest](https://github.com/BetonQuest/BetonQuest) with both [ItemsAdder](https://www.spigotmc.org/resources/%E2%9C%A8itemsadder%E2%AD%90emotes-mobs-items-armors-hud-gui-emojis-blocks-wings-hats-liquids.73355/) and [Nexo](https://polymart.org/product/6901/nexo).*

## 👤 Author: MrJimin

### 📦 Compatibility

- **BetonQuest**: `3.0.0-DEV-306`
- **ItemsAdder**: `2.1.25+`
- **Nexo**: `latest version`

---

### 🛠 Requirements

- Minecraft `1.20+`
- BetonQuest `3.0.0-DEV-306` or higher
- ItemsAdder `2.1.25+`
- Nexo (latest version)

---

### 📥 Installation

1. Download the plugin jar from the [Releases(Spigot)](https://www.spigotmc.org/resources/betonquestaddon.120813/) page.
2. Place it into your server’s `/plugins` directory.
3. Restart the server.
4. Configure your BetonQuest quest files using the syntax below.

---

### 🧰 BetonQuest Integration: ItemsAdder & Nexo

This addon supports both **ItemsAdder** and **Nexo** in BetonQuest conditions, events, and objectives.

---

#### ✅ Conditions

Check if a block exists at a specific location.

- `iaBlock <blockId> <x;y;z;world>`  
  e.g. `iaBlock itemsadder:ruby_ore 40;72;3;world`

- `nxBlock <blockId> <x;y;z;world>`  
  e.g. `nxBlock ruby_ore 40;72;3;world`

---

#### ⚙️ Events

Change a block or play an animation.

- `iaBlockAt <blockId> <x;y;z;world>`  
- `nxBlock <blockId> <x;y;z;world>`

- `iaPlayAnimation <animationID>` *(Nexo not supported)*

---

#### 🎯 Objectives

Players must break or place a specific number of blocks.

- `iaBlockBreak / nxBlockBreak <blockId> [amount:x] [notify:number]`  
  e.g. `iaBlockBreak itemsadder:ruby_ore amount:5 notify:1`  
  e.g. `nxBlockBreak ruby_ore amount:5 notify:1`

- `iaBlockPlace / nxBlockPlace <blockId> [amount:x] [notify:number]`

---

### ⚙️ Configuration (`config.yml`)
> ⚠️ **Important (Temporary)!**
> This setting is designed to reduce the need to manually reload BetonQuest quests when values are updated.
> Enabling it will automatically reload BetonQuest upon certain events to reflect changes faster.
> Please note this feature is **experimental** and may be changed or removed in future BetonQuest updates.
> Use it **cautiously** and only as a temporary solution.
```yaml
# Enables automatic BetonQuest reload when Nexo's NexoItemsLoadedEvent is triggered.
# Default: false. Set to true and restart the server to enable this feature.
setting:
  auto-reload: false
```

---

#### 📦 YAML Example

```yaml
items:
  itemsAdderRuby: ia iasurvival:ruby     # ItemsAdder item
  nexoRuby: nexo ruby                    # Nexo item

conditions:
  hasItemsAdderRuby: item itemsAdderRuby
  hasNexoRuby: item nexoRuby

events:
  giveItemsAdderRuby: give itemsAdderRuby
  giveNexoRuby: give nexoRuby
````

---

### 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

### 🙏 Credits

Some code and ideas were inspired by and adapted from [TheosRee/BetonQuestItemsAdder](https://github.com/TheosRee/BetonQuestItemsAdder). Many thanks to the original author for their work!

---

### 🙋 Support

If you have any issues or feature requests, feel free to open an issue on the [GitHub repository](https://github.com/MrJimin/BetonQuestAddon).
