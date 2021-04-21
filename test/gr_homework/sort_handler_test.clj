(ns gr-homework.sort-handler-test
  (:require [clojure.test :refer [deftest are]]
            [gr-homework.sort-handler :as gr]))

;; Defining mock data and helper fns
(def datata
  [["name" "age" "hobby"]
   ["frodo" "50" "zzzing around the shire"]
   ["samwise" "38" "protecting master frodo"]
   ["pippin" "27" "throwing stones into pits in abandoned mines"]
   ["merry" "36" "riding ents"]])

(def datata-by-name
  [["frodo" "50" "zzzing around the shire"]
   ["merry" "36" "riding ents"]
   ["pippin" "27" "throwing stones into pits in abandoned mines"]
   ["samwise" "38" "protecting master frodo"]])

(def datata-by-age-desc
  [["frodo" "50" "zzzing around the shire"]
   ["samwise" "38" "protecting master frodo"]
   ["merry" "36" "riding ents"]
   ["pippin" "27" "throwing stones into pits in abandoned mines"]])

(def datata-by-hobby
  [["samwise" "38" "protecting master frodo"]
   ["merry" "36" "riding ents"]
   ["pippin" "27" "throwing stones into pits in abandoned mines"]
   ["frodo" "50" "zzzing around the shire"]])

(def name-index (gr/get-index "name" datata 0))
(def age-index (gr/get-index "age" datata 1))
(def hobby-index (gr/get-index "hobby" datata 2))

(defn name-sort
  "Comparator that sorts by name, ascending"
  [a b]
  (compare (get a name-index) (get b name-index)))

(defn age-sort-desc
  "Comparator that sorts by age, descending"
  [a b]
  (compare (get b age-index) (get a age-index)))

(defn hobby-sort
  "Comparator that sorts by hobby, ascending"
  [a b]
  (compare (get a hobby-index) (get b hobby-index)))

(deftest sorting-patterns
  (are [srt d rtn] (= (into [] (sort srt (rest d))) rtn)
    name-sort datata datata-by-name
    age-sort-desc datata datata-by-age-desc
    hobby-sort datata datata-by-hobby))
