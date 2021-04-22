(ns gr-homework.handler.sort-test
  (:require [clojure.test :refer [deftest are testing is]]
            [gr-homework.converter.maps :as m]
            [gr-homework.handler.sort :as sh]))

;; Defining mock data and helper fns
(def datata
  [["LastName" "DateOfBirth" "Gender"]
   ["Michaels" "1/1/1970" "M"]
   ["Steward" "5/30/1984" "M"]
   ["Shelley" "11/9/1989" "F"]
   ["Masterson" "12/25/1979" "F"]])

;; Results should be descended on last name
(def datata-by-lastname
  [{:LastName "Steward", :DateOfBirth "5/30/1984", :Gender "M"}
   {:LastName "Shelley", :DateOfBirth "11/9/1989", :Gender "F"}
   {:LastName "Michaels", :DateOfBirth "1/1/1970", :Gender "M"}
   {:LastName "Masterson", :DateOfBirth "12/25/1979", :Gender "F"}])

;; Results should be ascending on date of birth
(def datata-by-dob
  [{:LastName "Michaels", :DateOfBirth "1/1/1970", :Gender "M"}
   {:LastName "Masterson", :DateOfBirth "12/25/1979", :Gender "F"}
   {:LastName "Steward", :DateOfBirth "5/30/1984", :Gender "M"}
   {:LastName "Shelley", :DateOfBirth "11/9/1989", :Gender "F"}])

;; Results should be gender, then last name
(def datata-by-gender
  [{:LastName "Masterson", :DateOfBirth "12/25/1979", :Gender "F"}
   {:LastName "Shelley", :DateOfBirth "11/9/1989", :Gender "F"}
   {:LastName "Michaels", :DateOfBirth "1/1/1970", :Gender "M"}
   {:LastName "Steward", :DateOfBirth "5/30/1984", :Gender "M"}])

(deftest sorting-patterns
  (are [srt d rtn]
       (= (into [] (sort srt (m/matrix-to-map d))) rtn)
    sh/compr-lastname datata datata-by-lastname
    sh/compr-dob      datata datata-by-dob
    sh/compr-gender   datata datata-by-gender))

(deftest date-formatter
  (testing "Does it convert M/D/YYYY to YYYYMMDD"
    (is (= (sh/date-format "3/4/1955") "19550304"))
    (is (= (sh/date-format "10/1/2005") "20051001"))
    (is (= (sh/date-format "9/10/2020") "20200910"))))