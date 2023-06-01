# EELang

EELang is a MAL language intended for modelling attack paths in the Distributed Energy Resources environment.

This project has the following structure:

* The file `pom.xml` is the Maven configuration file of the project.
* The directory `src/main/mal` contains the MAL specification
  `EELang.mal`, which is the MAL specification of EELang.
* The directory `src/main/resources/icons` contains SVG icons for the
  assets in EELang.
* The directory `src/test/java/org/mal_lang/eelang/test`
  contains the unit tests of EELang.

## Apache Maven

[Apache Maven](https://maven.apache.org/) is a build tool and
dependency management tool for Java projects. You can read more about
Maven at <https://en.wikipedia.org/wiki/Apache_Maven>. Follow the
instructions at <https://maven.apache.org/download.cgi> to download
Maven, and follow the instructions at
<https://maven.apache.org/install.html> to install Maven.

## Building EELang and running the unit tests

The
[MAL compiler](https://github.com/meta-attack-language/malcompiler)
compiles MAL specifications (`.mal` files) into different formats,
using different backends. The reference backend generates Java code
that is suitable for testing purposes and evaluating your language.
The securiCAD backend generates a `.jar` file that can be used with
[foreseeti](https://foreseeti.com)'s products, including
[securiCAD](https://foreseeti.com/securicad), which is a tool
that can be used to graphically create models using your language and
to simulate attacks on those models.

### Building with the reference backend and running the unit tests

To compile EELang with the reference backend of the MAL compiler
and then run the unit tests, execute the following command from the
`EELang` directory:

```
mvn test
```

This will invoke the MAL compiler's reference backend to generate
`.java` files under `target/generated-test-sources`. These `.java`
files and the unit tests in `src/test/java` will then be compiled
into `.class` files under `target/test-classes`. The unit tests will
then finally be executed.

To only compile EELang into `.java` files, execute the following
command:

```
mvn generate-test-sources
```

To compile EELang into `.java` files and then compile these
`.java` files and the unit tests in `src/test/java` into `.class`
files, execute the following command:

```
mvn test-compile
```

To run a specific test class, execute the following command:

```
mvn test -Dtest=TestEELang
```

Where `TestEELang` is the test class.

To run a specific test method in a test class, execute the following
command:

```
mvn test -Dtest=TestEELang#testNoPassword
```

Where `TestEELang` is the test class and `testNoPassword` is the
test method.

### Building a securiCAD compatible .jar file

In order to use your language with foreseeti’s products, it is necessary to build a securiCAD-compatible `.jar` file and to obtain a copy of the securiCAD software. 

foreseeti offers a free version of the securiCAD Professional tool for MAL developers. Sign up [here](https://foreseeti.com/foreseeti-getting-started/) to get access to the securiCAD Professional tool as well as instruction on how to access the foreseeti Maven repository. 

To compile EELang with the securiCAD backend of the MAL
compiler, execute the following command:

```
mvn package -PsecuriCAD
```

The resulting `.jar` file will be located in
`target/eelang-1.0.0.jar`.

If you don't want to run the unit tests when building a securiCAD
compatible `.jar` file, execute the following command:

```
mvn clean package -PsecuriCAD -Dmaven.test.skip=true
```

## License

All files distributed in the EELang project are licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0), except for the following files:

| File | License |
| --- | --- |
| [`Host.svg`](src/main/resources/icons/Host.svg) | <img src="src/main/resources/icons/Host.svg" alt="Host.svg" width="32" height="32"/> "[Computer](https://thenounproject.com/term/computer/576625/)" icon by [✦ Shmidt Sergey ✦](https://thenounproject.com/monstercritic/) from [the Noun Project](https://thenounproject.com/) is licensed under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/). |
| [`Network.svg`](src/main/resources/icons/Network.svg) | <img src="src/main/resources/icons/Network.svg" alt="Network.svg" width="32" height="32"/> "[Network](https://thenounproject.com/term/network/691907/)" icon by [✦ Shmidt Sergey ✦](https://thenounproject.com/monstercritic/) from [the Noun Project](https://thenounproject.com/) is licensed under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/). |
| [`Password.svg`](src/main/resources/icons/Password.svg) | <img src="src/main/resources/icons/Password.svg" alt="Password.svg" width="32" height="32"/> "[Lock](https://thenounproject.com/term/lock/576530/)" icon by [✦ Shmidt Sergey ✦](https://thenounproject.com/monstercritic/) from [the Noun Project](https://thenounproject.com/) is licensed under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/). |
| [`User.svg`](src/main/resources/icons/User.svg) | <img src="src/main/resources/icons/User.svg" alt="User.svg" width="32" height="32"/> "[User](https://thenounproject.com/term/user/581261/)" icon by [✦ Shmidt Sergey ✦](https://thenounproject.com/monstercritic/) from [the Noun Project](https://thenounproject.com/) is licensed under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/). |

See [`LICENSE`](LICENSE) and [`NOTICE`](NOTICE) for details.
