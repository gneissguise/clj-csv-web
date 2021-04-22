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
