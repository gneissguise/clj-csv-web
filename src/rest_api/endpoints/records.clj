(ns rest-api.endpoints.records
  (:require [clojure.data.json :as json]
            [gr-homework.handler.sort :as sh]))

;;(sh/sort-keymap srt)
(defn get!
  ([]
  (json/write-str
   {:msg "get all records"}))

  ([args]
  (json/write-str
   {:msg (str "get " args (sh/sort-keymap args))})))

(defn post!
  [args]
  (json/write-str
   {:msg (str "post " args)}))