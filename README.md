# gr-homework

This is a project to demonstrate some of my basic skills in clojure, with the following guidelines:

1. Build a system to parse and sort a set of records
2. Build a REST API to access your system

```clojure
(ns gr-homework.github)
(def project-status {:step1 "Completed" :step2 "In Progress"})
```

## Dependencies

- openjdk-11
- Clojure 1.10
- Leinengen

## How To Run the Command Line Utility
### Installation
   
    $ lein install
### Usage
   
    // Running tests
    $ lein test

    // Running app via leiningen:
    $ lein run -- [args]

    // Installing application
    $ lein install

    // Running jar:
    $ java -jar target/gr-homework-1.0.0-standalone.jar [args]
### Options

    Usage: gr-homework [options]

    Options:
    -s, --sort SORT  :Demo  Sort option
    -f, --file FILE  []     Files to load
    -h, --help              Help
    
### Examples

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

    // Check the directory 'examples/' for sample files!
    $ ls -l examples/
    -rw-r--r--. 1 user users 432 Apr 22 22:18 comma-delim.txt
    -rw-r--r--. 1 user users 473 Apr 22 02:19 pipe-delim.txt
    -rw-r--r--. 1 user users 379 Apr 22 22:18 space-delim.txt
## How To Run the Web Server Api

### Installation

    $ lein ring uberjar

### Usage

    // Running server from leiningen
    $ lein ring server

    OR

    // Running standalone server (After installation)
    $ java -jar target/uberjar/gr-homework-1.5.0-standalone.jar

A jetty http server will launch on port 3000 which you may access via curl or postman

### Examples

    // Check if the server is running
    $ curl localhost:3000
    Go time!

    // Accessing records
    //      /gender -> sort by Gender, LastName
    //      /dateofbirth -> DateOfBirth ascending
    //      /lastname -> LastName descending
    //
    // *NOTE: 'jq' is a json formatter for the command line.  It is 
    // a handly tool if you use 'curl' frequently. Look for it in your
    // package manager!
    $ curl -s  localhost:3000/records/gender | jq
    {
        "result": [
        {
            "LastName": "Edwards",
            "FirstName": "Noemi",
            "Gender": "F",
            "FavoriteColor": "Rosegold",
            "DateOfBirth": "6/25/8705"
        },
        {
            "LastName": "Emmott",
            "FirstName": "Aisha",
            "Gender": "F",
            "FavoriteColor": "Orange",
            "DateOfBirth": "1/2/3027"
        },
        {
            "LastName": "Harrington",
            "FirstName": "Elisabeth",
            "Gender": "F",
            "FavoriteColor": "Maroon",
            "DateOfBirth": "10/8/0813"
        },
    ...

    // Adding a record via POST
    // NOTE: check for your record at the end of the return request
    $ curl -X POST -H 'content-type: application/json' \
        -d '{"LastName": "Dundee","FirstName": "Crocodile","Gender": "M","DateOfBirth": "12/15/1957","FavoriteColor": "Red"}' \
        http://localhost:3000/records | jq
    {
        "result": [
            ... Lots of records
            {
                "LastName": "Dundee",
                "FirstName": "Crocodile",
                "Gender": "M",
                "DateOfBirth": "12/15/1957",
                "FavoriteColor": "Red"
            }
        ]
    }

    // Updating a record - Provide the LastName and FirstName otherwise
    // the request will be treated as a new record and add to the list.
    $ curl -X POST -H 'content-type: application/json' \
        -d '{"LastName": "Dundee","FirstName": "Crocodile","FavoriteColor": "Green"}' \
        http://localhost:3000/records | jq
    {
        "result": [
            ... Lots of records
            {
                "LastName": "Dundee",
                "FirstName": "Crocodile",
                "Gender": "M",
                "DateOfBirth": "12/15/1957",
                "FavoriteColor": "Green"
            }
        ]
    }

## Test Coverage
    |----------------------------+---------+---------|
    |                  Namespace | % Forms | % Lines |
    |----------------------------+---------+---------|
    |     gr-homework.controller |   77.24 |   89.19 |
    | gr-homework.converter.maps |  100.00 |  100.00 |
    |           gr-homework.core |   11.11 |   30.77 |
    |    gr-homework.handler.cli |   93.68 |   96.15 |
    |   gr-homework.handler.file |  100.00 |  100.00 |
    |  gr-homework.handler.print |  100.00 |  100.00 |
    |   gr-homework.handler.sort |   89.13 |   92.00 |
    |                rest-api.db |   79.41 |   86.67 |
    | rest-api.endpoints.records |  100.00 |  100.00 |
    |           rest-api.handler |   69.47 |   95.00 |
    |----------------------------+---------+---------|
    |                  ALL FILES |   81.91 |   89.34 |
    |----------------------------+---------+---------|

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
