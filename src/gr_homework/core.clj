(ns gr-homework.core
  (:require [gr-homework.handler.cli :as cli]
            [gr-homework.controller :as ctrl])
  (:gen-class))

(defn exit 
  "Gracefully handle exiting the application on error"
  [status msg]
  (println msg)
  (System/exit status))

(defn start-up
  "Lifts the passed args from options by keyword and pass to the display endpoint on the controller"
  [options]
  (let [srt (:sort options)
        files (:file options)]
    (ctrl/display files srt)))

(defn -main
  "I do a bit more now!"
  [& args]
  (let [{:keys [options exit-message ok?]} (cli/validate-args args)]
    (if exit-message
      (exit (if ok? 0 1) exit-message)
      (start-up options))))
