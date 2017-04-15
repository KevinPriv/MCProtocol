# MCProtocol
MCProtocol is a library that implements the Minecraft protocol from scratch(PC version, multiplayer protocol).

This library is packet-based which means it's incredibly easy to extend yourself.

I wrote this as a challenge for myself incase I got bored. Feel free to fork since I will probably discontinue this project sometime in the near future.

## Current Features
- Fetch MOTD
- Login to servers
- Send packets/chat
- Easy to extend

## Roadmap/Backlog
- Tidy up source code and improve code quality
- Add JavaDoc to everything
- Better, more efficient packet management
- Better use of the log
- Unit testing
- Support for SMP Map format/MP worlds
- Player actions such as walking, jumping etc...
- Player stuff like inventory management
- Support for other entities(such as pigs, xp orbs, etc...)
- Optional JavaFX GUI wrapper
- Plugin/extension support(custom, allows 3rd party protocol/packet implementations to extend and improve the current implementation)
- Better and more generic event management throughout the protocol.
- Event bus with the new generic event management(custom, can connect to plugins)

## Examples
Example code can be found in the [example](https://github.com/Camphul/MCProtocol/tree/master/src/main/java/example) package.

## Credit/Attribution
Major thanks to [wiki.vg](http://wiki.vg/Protocol) for their protocol specification wiki. Without them I would not be able to create this.

I also want to credit:
- DarkStorm: I got his encryption util class and check his older minecraft bot out for ideas.
- Stevecce10: Also wrote an implementation of the protocol, helped me when I could not quite understand the wiki.vg description.

Other code credit is given in the source files themselves.

## License
This project uses the Apache License 2.0 which can be found in LICENSE.txt