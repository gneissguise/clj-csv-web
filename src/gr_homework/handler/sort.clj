(ns gr-homework.handler.sort
  (:require [clojure.string :as string]))

;; Helper functions
(defn sort-key
  "Returns proprer sort keyword based on string [s], defaults to :LastName"
  [s]
  (let [srt (string/lower-case s)]
    (case srt
      "lastname" :LastName
      "gender" :Gender
      "dateofbirth" :DateOfBirth
      "demo" :Demo
      :LastName)))

(defn date-format
  "Reformats a date string to YYYYMMDD by string splitting, zero filling and rearranging, for sorting purposes"
  [s]
  (let [[m d y] (string/split s #"/")]
    (string/replace
     (format "%1$4s%2$2s%3$2s" y m d)
     " " "0")))

;; Comparators functions
(def compr-gender
  #(compare [(:Gender %1) (:LastName %1)]
            [(:Gender %2) (:LastName %2)]))
(def compr-dob
  #(compare [(date-format (:DateOfBirth %1))]
            [(date-format (:DateOfBirth %2))]))

(def compr-lastname
  #(compare [(:LastName %2)]
            [(:LastName %1)]))

(defn lookup-sort
  "Pull up a table heading and a sort comparator by keyword [k]"
  [k]
  (case k
    :Gender      ["Sort by Gender, then LastName ascending"
                  compr-gender]
    :DateOfBirth ["Sort by DateOfBirth ascending"
                  compr-dob]
    :LastName    ["Sort by LastName descending"
                  compr-lastname]))
