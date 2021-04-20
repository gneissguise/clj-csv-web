# gr-homework

## The 411

This is a project to demonstrate some of my basic skills in clojure.


## The guidelines

1. Build a system to parse and sort a set of records
2. Build a REST API to access your system


### Current status:
```clojure
(ns gr-homework.github)
(def status-map 
  {:step1 {:status :in-progress}
   :step2 {:status :not-started}})
```


## Dependencies
- openjdk-11
- Clojure 1.10
- Leinengen


## How to run
### Tests:
```bash
$> lein test
```


### Main application:
```bash
$> lein run `$args`
```


## Files
### Project contents:
```bash
$> tree
.
└── README.md

0 directories, 1 file
```