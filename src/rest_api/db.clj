(ns rest-api.db
  (:require [gr-homework.controller :as ctrl]
            [gr-homework.handler.sort :as sh]))

;; I am just going to use an atom now, it wont be persistent 
(def store (atom []))

(def source ["./examples/comma-delim.txt"
             "./examples/pipe-delim.txt"
             "./examples/space-delim.txt"])

(defn compar
  "Returns sort comparator from the sort handler by keyword [kwd]"
  [kwd]
  (last
   (sh/lookup-sort kwd)))

(defn file->vec
  "Reaches out to the controller to process
   file(s) [f] into a vec"
  [f]
  (vec
   (ctrl/process-file f)))

(defn load!
  "Loads fileset [f] into store"
  [f]
  (when-let [v (file->vec f)]
    (reset! store v)))

(defn select-using
  "Selects records from store and applies a sort to them.
   options:
     :sort [keyword]"
  [options]
  (when (empty? @store)
    (load! source))
  (when-let [st @store]
    (vec (sort (compar (:sort options)) st))))

(defn name?
  "Compares maps [a] and [b] on :Firstname and :Lastname"
 [a b]
 (and (= (:LastName a) (:LastName b))
      (= (:FirstName a) (:FirstName b)))) 

(defn store-contains?
  "Checks to see if there's a person match by LastName and FirstName"
  [s r]
  (letfn [(nmatch [x y] (if (false? x) (name? y r) x))]
    (reduce nmatch false s)))
  
(defn person-merge
  "Merges map [r] into col [col] on matching map with name?"
  [col r]
  (letfn [(nmerge [m] (if (name? m r) (merge m r) m))]
    (mapv nmerge col)))

(defn update!
  "Updates record [r] in the store. Updates match on LastName and
   FirstName.  If record does not exist, a new one is added"
  [r]
  (if (store-contains? @store r)
    (swap! store person-merge r)
    (swap! store into [r])))
