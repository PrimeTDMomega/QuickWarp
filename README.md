# QuickWarp
A simple PaperMC plugin that allows players to switch between dimensions using commands.

This is mostly for Minecraft Survival servers with many players on the same map in maybe a Lifesteal, Hardcore or Factions setup basically removing the hastle of end portals and portal trapping/portal crashing by directly teleporting a player to the dimension.

## Commands
Currently user has ability to switch between dimensions but I want to add functionality that allows admins to set specific teleport spots or waypoints.

```mclang
/overworld
/nether
/end
```
All 3 commands take player to the respective dimension. If player is already in the dimension they type command for they are hit with an error message in chat saying.
```
You cannot teleport to the dimenion you are in...
```
Though I plan to make the message configurable through the `config.json` file produced by the plugin.

# Compile
**Windows Commands**
```batch
gradlew setupdecompworkspace
gradlew clean
gradlew build
```
**Linux Commands**
```shell
./gradlew setupdecompworkspace
./gradlew clean
./gradlew build
```

# Contribute
If you want to contribute then fork the repository, make changes you want  and then make a pull request with a good description/title.

# LICENSE
This repository/project uses the GPL-3.0 LICENSE view [here](LICENSE)

# Credit
[prime](https://github.com/PrimeTDMomega/) - Cool HOOman
<br>
**More Collaborators Coming SOON...**