# Inventory Viewer

The Inventory Viewer plugin allows players to view other players' inventories in a user-friendly GUI. In addition to viewing the inventory contents, the plugin provides details such as health points, food level, and experience level of the target player.

---

## Features

- **Inventory Viewing**: Open and browse other players' inventories seamlessly.
- **Player Stats**: See the health points, food level, and experience level of the targeted player.
- **Real Time Updating**: The GUI updates every 10 ticks (0.5 seconds).
- **Easy Commands**: Multiple command aliases for convenience.

---

## Commands

You can use the following commands to access the inventory viewer:

- `/inventoryviewer <player>`
- `/inventoryview <player>`
- `/inv <player>`

### Permissions
Ensure players have the required permissions to use the commands:
- `inventoryviewer.use`: Grants access to view other players' inventories.

---

## Usage

To use the plugin, simply run one of the commands listed above, replacing `<player>` with the name of the player whose inventory you want to view. For example:

```
/inventoryviewer Steve
```
This command will open a GUI showing Steve's inventory, health points, food level, and experience level.

---

## Example of Usage

1. **Basic Usage**:
   - `/inventoryviewer Alex`: Opens Alex's inventory and displays their stats.

2. **Aliases for Convenience**:
   - `/inv Alex`: A quicker way to access Alex's inventory.

3. **Error Handling**:
   - If the player is offline or the command is used incorrectly, the plugin will notify you with an error message.

---

## Support

For questions, feature requests, or bug reports, feel free to reach out:
- [GitHub Issues](https://github.com/HenriqueMichelini/inventory_viewer/issues)

---
