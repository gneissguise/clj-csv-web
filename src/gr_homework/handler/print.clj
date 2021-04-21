(ns gr-homework.handler.print
  (:require [clojure.pprint :as p]))

(defn print-table
  "Uses pretty print to format and print the vector table to the console"
  [v]
  (let [[header & body] v]
    (p/pprint header)
    (doseq [b body]
      (p/pprint b))))

;; Reducer for bubbling up the max count of an element in a matrix
(def col-size
  (fn
    [a b]
    (let [z (mapv vector b a)]
      (into [] (for [[x y] z]
                 (if (> x y) x y))))))

;; Mapper that returns the char size of an element in a 2D matrix shape
(def get-counts
  (fn [v] (mapv #(mapv count %) v)))

(defn get-col-size
  "Returns the max element size for each column in a matrix"
  [v]
  (reduce col-size (get-counts v)))
