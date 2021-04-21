(ns gr-homework.sort-handler)

;; Helper functions
(defn get-index
  "Get the column index based on header row using s search term, and nil if no match"
  [s v]
  (.indexOf (first v) s))

(defn get-index-wrap
  "Wraps get-index, returns index of v header, using s term, and falling back to value fv or 0"
  [s v fv]
  (or (get-index s v) (or fv 0)))
