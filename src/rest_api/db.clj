(ns rest-api.db
  )

;; I am just going to use an atom now, it wont be persistent 
(def store (atom {}))

(defn load!
  "Loads fileset [f] into store"
  [f]
  nil)

(defn select
  "Selects records from store.
   options:
     :sort [keyword]
   "
  [options]
  nil)

(defn update!
  "Updates record [r] in the store.  If record 
   does not exist, a new one is added"
  [r]
  nil)