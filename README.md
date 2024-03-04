# QuickWarp

A simple PaperMC plugin that allows players to switch between dimensions using commands.

This is mostly for Minecraft Survival servers with many players on the same map in maybe a Lifesteal, Hardcore or Factions setup basically removing the hastle of end portals and portal trapping/portal crashing by directly teleporting a player to the dimension.

## Table of Contents
1. [Installation](#installation)
2. [Commands](#commands)
    - [Basic Dimension Teleportation](#basic-dimension-teleportation)
    - [Admin-defined Waypoints](#admin-defined-waypoints)
3. [Configuration](#configuration)
4. [Compilation](#compilation)
5. [Contribution](#contribution)
6. [License](#license)
7. [Credits](#credits)

## Installation
To install QuickWarp on your PaperMC server, follow these steps:

1. Download the latest release from the [Releases](https://github.com/YourUsername/QuickWarp/releases) page.
2. Place the downloaded JAR file into the `plugins` folder of your PaperMC server.
3. Restart the server.

## Commands
QuickWarp introduces simple yet powerful commands to enhance the teleportation experience for players and administrators.

### Configuration
The `config.json` is quite simple.
```json
{
  "errorMSG": "You are already in this dimension.",
  "permission": true
}
```
- `errorMSG`: Changes the message that is displayed in chat when a player runs a command for a dimension they are already in.
- `permission`: If set to `true` then plugin will check for OP perms on the player, in other words if set to `true` only OP players can use it if set to `false` all players can use it.

### Basic Dimension Teleportation
Players can use the following commands to switch between dimensions:

- `/overworld`: Teleports the player to the Overworld.
- `/nether`: Teleports the player to the Nether.
- `/end`: Teleports the player to the End.

If a player is already in the dimension they attempt to teleport to, an error message will be displayed in the chat.

### Admin-defined Waypoints
Administrators have the ability to set specific teleport spots or waypoints. This feature is under development and will be available in future releases.

## Configuration
QuickWarp comes with a `config.json` file that allows server administrators to customize certain aspects of the plugin. The file includes options to configure error messages and potentially other features as they are added.

Example `config.json`:
```json
{
  "errorMessage": "You cannot teleport to the dimension you are in..."
}
```

## Compilation
If you wish to compile QuickWarp yourself, follow these commands based on your operating system:

**Windows Commands:**
```batch
gradlew setupdecompworkspace
gradlew clean
gradlew build
```

**Linux Commands:**
```shell
./gradlew setupdecompworkspace
./gradlew clean
./gradlew build
```

## Contribution
If you'd like to contribute to QuickWarp, follow these steps:

1. Fork the repository.
2. Make the desired changes.
3. Submit a pull request with a detailed description and title.

## License
QuickWarp is licensed under the GPL-3.0 License. View the full license [here](LICENSE).

## Credits
- [Prime](https://github.com/PrimeTDMomega/): Cool HOOman - Project Creator and Maintainer.

**More Collaborators Coming SOON...**