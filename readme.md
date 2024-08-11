# Puzzle Loader Example Mod
> The example mod for [Puzzle Loader](https://github.com/PuzzleLoader/PuzzleLoader)

## How to test/build
For testing in the dev env, you can use the `gradle runLoader` task

For building, the usual `gradle buildBundleJar` task can be used. The output will be in the `build/libs/` folder

## Notes
- Most project properties can be changed in the `gradle.properties`
- To change author, description and stuff that is not there, edit `src/main/resources/puzzle.mod.json`
- The project name is defined in `settings.gradle`
- To add Puzzle mods in the build, make sure to use `internal` or `mod` rather than `implementation`
- To bundle your dependencies use `bundle` and run task `gradle buildBundleJar` or `gradle buildSlimJar` for a more cut down version.