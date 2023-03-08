# Insult-o-matic

Insult-o-matic is a Java program that generates random insults from a list of insults read from a file, and then uses text-to-speech technology to say the insult out loud using an external API service, ElevenLabs, the most realistic and versatile AI speech software.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Installation

To run the Insult-o-matic program, you will need to have the following installed on your machine:

- Java Development Kit (JDK) 8 or later
- The jlayer library, which can be downloaded from https://mvnrepository.com/artifact/javazoom/jlayer/1.0.1
- An API key for the ElevenLabs API text-to-speech service, which can be obtained by signing up at https://beta.elevenlabs.io/

## Usage

To use Insult-o-matic, follow these steps:

1. Clone the Insult-o-matic repository to your local machine.
2. Download the jlayer library and add it to your project's classpath.
3. Obtain an API key for the ElevenLabs API text-to-speech service and replace the value of the `API_KEY` constant in the `App.java` file with your own API key.
4. Create a file called `insults.txt` in the same directory as the `App.java` file, and add your list of insults to the file (one insult per line).
5. Compile the `App.java` file using `javac App.java`.
6. Run the `App` class using `java App`.

Once the program is running, you will be prompted to either get insulted or not. If you choose to get insulted, the program will generate a random insult from the `insults.txt` file and say it out loud using the ElevenLabs API text-to-speech service. You can continue to get insulted as many times as you like by typing "y" when prompted.

## Contributing

Contributions to Insult-o-matic are welcome! To contribute, please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
