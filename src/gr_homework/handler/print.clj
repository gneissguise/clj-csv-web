(ns gr-homework.handler.print
  (:require [clojure.pprint :as p]
            [gr-homework.handler.sort :as sh]))

(defn print-table
  "Uses pretty print to format and print the map table t to the console and sorts using c as comparator
   "
  ([t] (print-table t sh/compr-lastname))
  ([t c] (p/print-table (sort c t))))
