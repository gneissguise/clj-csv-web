(ns rest-api.endpoints.records
  (:require [clojure.data.json :as json]
            [gr-homework.handler.sort :as sh]
            [rest-api.db :as db]))

(defn get!
  ([]
   (get! {:sort :LastName}))

  ([args]
   (json/write-str
    {:result (db/select {:sort (sh/sort-keymap args)})})))

(defn post!
  [args]
  (json/write-str
   {:result
    (db/update! args)}))