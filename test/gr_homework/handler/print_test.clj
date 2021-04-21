(ns gr-homework.handler.print-test
  (:require [clojure.test :refer [deftest testing is]]
            [gr-homework.handler.print :as pr]))

;; Defining mock data and helper fns
(def datata
  [["name" "age" "hobby"]
   ["frodo" "50" "zzzing around the shire"]
   ["samwise" "38" "protecting master frodo"]
   ["pippin" "27" "throwing stones into pits in abandoned mines"]
   ["merry" "36" "riding ents"]])

(def tmp-data [["LastName" "FirstName" "Gender" "FavoriteColor" "DateOfBirth"]
               ["Frost" "Justin" "M" "chartreuse" "07/05/1981"]
               ["Kibbler" "Snot" "M" "snot green" "02/28/1977"]
               ["Greisiger" "Katie" "F" "bubble gum pink" "08/10/1983"]
               ["Castle" "Tella" "F" "brown" "10/21/1988"]])

(deftest calculate-col-size
  (testing "Calculates the max size of an element in a column of a matrix"
    (is (= (pr/get-col-size datata) [7 3 44]))
    (is (= (pr/get-col-size tmp-data) [9 9 6 15 11]))))
