(ns gr-homework.controller-test
  (:require [clojure.test
             :refer [deftest
                     testing
                     is
                     function?
                     use-fixtures
                     are]]
            [clojure.string :as string]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [gr-homework.controller :as ctrl]))
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
  (spit test-file "wombo,combo,Gender\nfoo,bar,U")
  (spit another-file "wombo,combo,Gender\nfizz,buzz,Z")

  ;; Run test fn
  (test-fn)

  ;; tear-down
  (del-when-exists test-file another-file))

;; Register fixtures
(use-fixtures :once file-fixture)

(deftest file-processing 
  (testing "that files merge"
    (is (= (ctrl/merge-file ["csv.test" "another-file.test"])
           [["wombo" "combo" "Gender"] ["foo" "bar" "U"] ["fizz" "buzz" "Z"]])))
  (testing "that file is processed into a map"
    (is (= (seq (ctrl/process-file ["csv.test" "another-file.test"]))
           '({:wombo "foo", :combo "bar" :Gender "U"} {:wombo "fizz", :combo "buzz" :Gender "Z"}))))
  (testing "that the display endpoint works"
    (is (= (with-out-str (ctrl/display ["csv.test" "another-file.test"] :Gender))
           "\n ============================================= \n    Sort by Gender, then LastName ascending \n =============================================\n\n| :wombo | :combo | :Gender |\n|--------+--------+---------|\n|    foo |    bar |       U |\n|   fizz |   buzz |       Z |\n"))))

