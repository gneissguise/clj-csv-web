(ns gr-homework.core
  (:require [gr-homework.handler.cli :as cli]
            [gr-homework.controller :as ctrl])
  (:gen-class))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn start-up
  [options]
  (let [srt (:sort options)
        files (:file options)]
    (ctrl/display files srt)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [{:keys [options exit-message ok?]} (cli/validate-args args)]
    (if exit-message
      (exit (if ok? 0 1) exit-message)
      (start-up options))))
