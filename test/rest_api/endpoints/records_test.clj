(ns rest-api.endpoints.records-test
  (:require [clojure.test :refer [deftest
                                  testing
                                  is
                                  function?
                                  use-fixtures
                                  are]]
             [rest-api.endpoints.records :as r]))

(def test-get-data (slurp "test/rest_api/endpoints/test-get-data.txt"))
(def test-post-data (slurp "test/rest_api/endpoints/test-post-data.txt"))
(def test-record {"LastName" "Brannigan"
                  "FirstName" "Zapp"
                  "Gender" "M"
                  "DateOfBirth" "4/1/2960"
                  "FavoriteColor" "Maroon"})

(deftest endpoints
  (testing "/get! endpoint"
    (is (= (r/get! {:sort :LastName})
           test-get-data)))
  (testing "/post! endpoint"
    (is (= (r/post! test-record)
           test-post-data))))
