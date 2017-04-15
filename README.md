# MCProtocol
This project implements the current Minecraft protocol in Java. Might give up on this some day.

This entire project is just a little challenge for me to get away from boredom.
I try to improve my code quality, make my code modular, apply design patterns and other concepts.

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

##Credit/Attribution
Major thanks to [wiki.vg](http://wiki.vg/Protocol) for their protocol specification wiki. Without them I would not be able to create this.

I also want to credit:
- DarkStorm: I got his encryption util class and check his older minecraft bot out for ideas.
- Stevecce10: Also wrote an implementation of the protocol, helped me when I could not quite understand the wiki.vg description.

Other code credit is given in the source files themselves.

## License
This project uses the Apache License 2.0 which can be found in LICENSE.txt