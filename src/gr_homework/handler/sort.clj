(ns gr-homework.handler.sort
  (:require [clojure.string :as s]))

;; Helper functions
(defn date-format
  "Reformats a date string to YYYYMMDD by string splitting, zero filling and rearranging, for sorting purposes"
  [s]
  (let [[m d y] (s/split s #"/")]
    (s/replace
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