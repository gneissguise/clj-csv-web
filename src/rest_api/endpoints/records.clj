(ns rest-api.endpoints.records
  (:require [clojure.data.json :as json]
            [gr-homework.handler.sort :as sh]
            [rest-api.db :as db]))

(defn get!
  [args]
  (let [token (sh/sort-key args)
        col (db/select-using {:sort token})]
    (json/write-str {:result col})))

(defn post!
  [args]
  (json/write-str
   {:result
    (db/update! args)}))