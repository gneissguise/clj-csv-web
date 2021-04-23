(ns rest-api.db
  (:require [gr-homework.controller :as ctrl]
            [gr-homework.handler.sort :as sh]))

;; I am just going to use an atom now, it wont be persistent 
(def store (atom []))

(def source ["./examples/comma-delim.txt"
             "./examples/pipe-delim.txt"
             "./examples/space-delim.txt"])

(defn get-cmpr
  "Gets comparator from sort handler
   (last pos of returned coll)"
  [c]
  (last c))

(defn sorter
  "Sort vec [v] using comparator [cmpr]"
  [v cmpr]
  (sort cmpr v))

(defn file->vec
  "Reaches out to the controller to process
   file(s) [f] into a vec"
  [f]
  (vec 
   (ctrl/process-file f)))

(defn process
  "Process data from files [f] and sort using option [srt]"
  [f srt]
  (-> (file->vec f)
      (sorter
       (-> srt
           sh/lookup-sort
           get-cmpr))))

(defn load!
  "Loads fileset [f] into store"
  [f options]
  (let [{srt :sort} options]
    (when-let [v (process f srt)]
      (swap! store into v))))

(defn select
  "Selects records from store.
   options:
     :sort [keyword]
   "
  [options]
  (when (empty? @store)
    (load! source options))
  @store)

(defn update!
  "Updates record [r] in the store.  If record 
   does not exist, a new one is added"
  [r]
  nil)