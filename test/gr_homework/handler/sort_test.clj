(ns gr-homework.handler.sort-test
  (:require [clojure.test :refer [deftest are testing is function?]]
            [gr-homework.handler.sort :as sh]))

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

(def name-index (sh/get-index "name" datata 0))
(def age-index (sh/get-index "age" datata 1))
(def hobby-index (sh/get-index "hobby" datata 2))

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


(def tmp-data [["LastName" "FirstName" "Gender" "FavoriteColor" "DateOfBirth"]
               ["Frost" "Justin" "M" "chartreuse" "07/05/1981"]
               ["Kibbler" "Snot" "M" "snot green" "02/28/1977"]
               ["Greisiger" "Katie" "F" "bubble gum pink" "08/10/1983"]
               ["Castle" "Tella" "F" "brown" "10/21/1988"]])
(def get-tmp-data-index (sh/get-index-factory tmp-data))
(def gender-index (get-tmp-data-index "Gender" 2))
(def birthdate-index (get-tmp-data-index "DateOfBirth" 4))
(def lastname-index (get-tmp-data-index "LastName" 0))

(deftest get-index-builder
  (are [col-index actual] (= col-index actual)
    gender-index 2
    birthdate-index 4
    lastname-index 0))

(deftest comparator-builder
  (testing "Does comparator return a fn"
    (is (function? (sh/comparator-factory 0)))))
