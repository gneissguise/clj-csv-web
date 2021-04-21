(ns gr-homework.handler.sort)

;; Helper functions
(defn comparator-factory
  "Returns a comparator function for the value of vec at index i"
  [i]
  (fn [a b] (compare (get a i) (get b i))))

(defn get-index
  "Wraps get-index, returns index of v header, using s term, and falling back to value fv or 0"
  ([s v]
   (get-index s v 0))
  ([s v fv]
   (or (.indexOf (first v) s) fv)))

(defn get-index-factory
  "Generates a get-index fn populated with a data vector for reuseability"
  [v]
  (fn [s fv] (get-index s v fv)))
