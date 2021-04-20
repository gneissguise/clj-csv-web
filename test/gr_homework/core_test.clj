(ns gr-homework.core-test
  (:require [clojure.test
             :refer [deftest
                     testing
                     is
                     function?
                     use-fixtures]]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [gr-homework.core :as gr]))

;; Global declarations and helper functions
(def test-file "csv.test")
(def another-file "another-file.test")
(defn exists?
  "check if file exists"
  [f]
  (.exists (io/file f)))

(defn delete-file
  "deletes file"
  [f]
  (io/delete-file f))

(defn del-when-exists
  "fn composed of a when predicate to check if files exists, then deletes them"
  [& files]
  (doseq [f files]
    (when (exists? f)
      (delete-file f))))

;; Fixtures
(defn file-fixture
  "Fixture for csv file setup"
  [test-fn]
  ;; Generate files for csv-file-processing
  (del-when-exists test-file another-file)
  (spit test-file "wombo,combo\nfoo,bar")

  ;; Run test fn
  (test-fn)

  ;; tear-down
  (del-when-exists test-file another-file))

;; Register fixtures
(use-fixtures :once file-fixture)

;; Tests
(deftest main-func
  (testing "-main prints: Hello, World!"
    (is (= "Hello, World!\n" (with-out-str (gr/-main))))))

(deftest dependency-check
  ;; Simple dependency sanity check
  (testing "clojure.data.csv"
    (is (function? csv/read-csv)))
  (testing "clojure.java.i(o"
    (is (function? io/reader))))

(deftest csv-file-processing
  (testing "read-file reads from a file"
    (is (= (gr/read-file test-file) [["wombo" "combo"]
                                     ["foo" "bar"]])))
  (testing "write-file writes to a file"
    (is (= (do
             (gr/write-file another-file
                            [["name" "age" "hobby"]
                             ["frodo" "50" "lazing around the shire"]
                             ["samwise" "38" "protecting master frodo"]
                             ["pippin" "27" "throwing stones into pits in abandoned mines"]
                             ["merry" "36" "riding ents"]])
             (slurp another-file))
           (str "name,age,hobby\n"
                "frodo,50,lazing around the shire\n"
                "samwise,38,protecting master frodo\n"
                "pippin,27,throwing stones into pits in abandoned mines\n"
                "merry,36,riding ents\n")))))