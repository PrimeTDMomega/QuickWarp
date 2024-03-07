
# QuickWarp

A simple PaperMC plugin that allows players to switch between dimensions using commands.

This is mostly for Minecraft Survival servers with many players on the same map in maybe a Lifesteal, Hardcore or Factions setup basically removing the hastle of end portals and portal trapping/portal crashing by directly teleporting a player to the dimension.

## Installation
To install QuickWarp on your PaperMC server, follow these steps:

1. Download the latest release from the [Releases](https://github.com/YourUsername/QuickWarp/releases) page.
2. Place the downloaded JAR file into the `plugins` folder of your PaperMC server.
3. Restart the server.

## Commands


- `/qw overworld`: Teleports the player to the Overworld.
- `/qw nether`: Teleports the player to the Nether.
- `/qw end`: Teleports the player to the End.

If a player is already in the dimension they attempt to teleport to, an error message will be displayed in the chat.

## Configuration
The `config.json` is quite simple.
```json
{
  "permission": true,
  "errorMSG": "You are already in this dimension.",
}
```
- `errorMSG`: Changes the message that is displayed in chat when a player runs a command for a dimension they are already in.
- `permission`: If set to `true` then plugin will check for OP perms on the player, in other words if set to `true` only OP players can use it if set to `false` all players can use it.

## Future Features
Features I wish to implement, if you're a paper dev then feel free to help.

1. Allow for setup of pre-determined waypoints that can be named and warped to.
2. Make a UI for warping (maybe).
3. Add a particle effect on warp.
4. Add temporary damage immunity on warp.

These will all be added (except maybe number 2 most coz I'm a bad developer and I think making a GUI will kill the point of the plugin being a fast means of travel).
## Compilation
If you wish to compile QuickWarp yourself, follow these commands based on your operating system:

**Windows Commands:**
```batch
gradlew clean
gradlew build
```

**Linux Commands:**
```shell
./gradlew clean
./gradlew build
```

## Contribution
If you'd like to contribute to QuickWarp, follow these steps:

1. Fork the repository.
2. Make the desired changes.
3. Submit a pull request with a detailed description and title.

## Credits
<a href="https://github.com/PrimeTDMomega/QuickWarp/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=PrimeTDMomega/QuickWarp" />
</a>
