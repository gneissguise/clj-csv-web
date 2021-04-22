(ns gr-homework.handler.print
  (:require [clojure.pprint :as p]
            [gr-homework.handler.sort :as sh]))

;; I was originally going to take a different approach here,
;; but once i discovered print-table and the power of hashmaps,
;; I have greatly reduced my code overhead
(defn print-table
  "Uses pretty print to format and print the map table t to the console and sorts using c as comparator"

  ([t]
   (print-table t sh/compr-lastname))

  ([t c]
   (p/print-table (sort c t))))

(defn horiz-rule
  "Repeats '=' [n] times to generate a 'line'"
  [n]
  (apply str (repeat n "=")))

(defn print-heading
  "Prints a generated header using string [s]"
  [s]
  (let [hr (horiz-rule (+ (count s) 6))]
    (println \newline
             hr \newline
             "  " s \newline
             hr)))
