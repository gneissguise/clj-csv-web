(ns gr-homework.core
  (:require [clojure.tools.cli :as cli]
            [clojure.string :as s]
            [gr-homework.controller :as ctrl])
  (:gen-class))

(def sort-keymap {"lastname" :Lastname
                  "gender" :Gender
                  "dateofbirth" :DateOfBirth
                  "demo" :Demo})

(def cli-options
  [["-s"  "--sort SORT" "Sort option"
    :default :Demo
    :parse-fn #(get sort-keymap (s/lower-case %))
    :validate [#(not
                 (contains? sort-keymap (s/lower-case %)))
               "Valid sort options are 'LastName', 'Gender', 'DateOfBirth' or 'Demo' (runs all three on all three files)"]]
   ["-f" "--file FILE" "Files to load"
    :multi true
    :default ["./examples/comma-delim.txt"
              "./examples/pipe-delim.txt"
              "./examples/space-delim.txt"]
    :update-fn conj]
   ["-h" "--help" "Help"]])

(defn usage [options-summary]
  (->> ["gr-homework: This is my submission for a cli delimited file parser & REST api for a job interview."
        ""
        "Usage: gr-homework [options]"
        ""
        "Options:"
        options-summary
        ""]
       (s/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while processing your request:\n\n"
       (s/join \newline errors)))


(defn validate-args
  "Validate command line arguments. Either return a map indicating the program
  should exit (with a error message, and optional ok status), or a map
  indicating the action the program should take and the options provided."
  [args]
  (let [{:keys [options errors summary]} (cli/parse-opts args cli-options)]
    (cond
      (:help options) ; help => exit OK with usage summary
      {:exit-message (usage summary) :ok? true}
      errors ; errors => exit with description of errors
      {:exit-message (error-msg errors)}
      options
      {:options options}
      :else ; failed custom validation => exit with usage summary
      {:exit-message (usage summary)})))

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
  (let [{:keys [options exit-message ok?]} (validate-args args)]
    (if exit-message
      (exit (if ok? 0 1) exit-message)
      (start-up options))))
