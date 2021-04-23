(ns gr-homework.handler.file
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as s]))

;; Delimiter that we fall back to
;; NOTE: clojure.data.csv will automatically default to a comma
;; if no  delimiter is provided to read-csv or write-csv.  I am using
;; defaults here for visibility's sake.
(defn detect-delim
  "Scan a string and look for a possible candidate out of comma ',' pipe '|', or space ' ' and return first match"
  [s]
  (let [comma? #(re-find #","  %)
        pipe?  #(re-find #"\|" %)
        space? #(re-find #" "  %)]
    (cond
      (comma? s) \,
      (pipe? s)  \|
      (space? s) \space
      :else      \,)))

(defn read-file
  "Reads the filename passed as argument f
   Returns vectors in 2d matrix"
  ([f]
   (let [file   (doall (slurp f))
         header (first (s/split-lines file))
         delim  (detect-delim header)]
     (csv/read-csv file :separator delim))))

(defn write-file
  "Formats vector v to csv and writes to file f"
  ([f v] (write-file f v \,))
  ([f v s]
   (with-open [file (io/writer f)]
     (csv/write-csv file v :separator s))))
