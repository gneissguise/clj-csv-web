(ns gr-homework.handler.print-test
  (:require [clojure.test :refer [deftest testing is]]
            [gr-homework.converter.maps :as m]
            [gr-homework.handler.print :as pr]))

;; Defining mock data and helper fns
(def tmp-data [["LastName" "FirstName" "Gender" "FavoriteColor" "DateOfBirth"]
               ["Tuffington" "Harvey" "M" "yellow" "3/13/1983"]
               ["James" "Greyson" "M" "blue" "9/4/1990"]
               ["Celery" "Amia" "F" "plum" "2/13/1976"]
               ["Zeta" "Velouria" "F" "black" "11/1/1980"]])

(def table-out (str "\n|  :LastName | :FirstName | :Gender | :FavoriteColor | :DateOfBirth |\n"
"|------------+------------+---------+----------------+--------------|\n"
"|       Zeta |   Velouria |       F |          black |    11/1/1980 |\n"
"| Tuffington |     Harvey |       M |         yellow |    3/13/1983 |\n"
"|      James |    Greyson |       M |           blue |     9/4/1990 |\n"
"|     Celery |       Amia |       F |           plum |    2/13/1976 |\n"))

(deftest table-printing
  (testing "that it prints the table"
    (is (=
         (with-out-str (pr/print-table (m/matr->map tmp-data)))
         table-out))))

(deftest heading-displays
  (testing "the horizontal rule"
    (is (= (pr/horiz-rule 5) "=====")))
  (testing "the header message"
    (is (= (with-out-str (pr/print-heading "It's worrrkiiing!"))
           "\n ======================= \n    It's worrrkiiing! \n =======================\n"))))
