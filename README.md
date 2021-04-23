# gr-homework

This is a project to demonstrate some of my basic skills in clojure, with the following guidelines:

1. Build a system to parse and sort a set of records
2. Build a REST API to access your system

```clojure
(ns gr-homework.github)
(def project-status {:step1 "Completed" :step2 "Not Started"})
```

## Dependencies

- openjdk-11
- Clojure 1.10
- Leinengen

## Installation
   
    $ lein install
## Usage
   
    // Running tests
    $ lein test

    // Running app via leiningen:
    $ lein run -- [args]

    // Installing application
    $ lein install

    // Running jar:
    $ java -jar target/gr-homework-1.0.0-standalone.jar [args]
## Options

    gr-homework: This is my submission for a cli delimited file parser & REST api for a job interview.

    Usage: gr-homework [options]

    Options:
        -s, --sort SORT  :Demo                                                                                    Sort option
        -f, --file FILE  ["./examples/comma-delim.txt" "./examples/pipe-delim.txt" "./examples/space-delim.txt"]  Files to load
        -h, --help                                                                                                Help

## Examples

    $ lein run -- --file /my/file/path.txt --sort DateOfBirth
    =================================== 
        Sort by DateOfBirth ascending 
    ===================================

    |  :LastName | :FirstName | :Gender | :FavoriteColor | :DateOfBirth |
    |------------+------------+---------+----------------+--------------|
    | Harrington |  Elisabeth |       F |         Maroon |    10/8/0813 |
    |     Leslie |      Moira |       F |           Gold |    2/23/0839 |
    |     Murray |      Lucas |       M |           Aqua |    4/19/1005 |
    |   Thatcher |     Trisha |       F |          Brown |    9/24/1068 |

## License


This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
