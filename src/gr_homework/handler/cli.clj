(ns gr-homework.handler.cli
  (:require [clojure.tools.cli :as cli]
            [clojure.string :as s]
            [gr-homework.handler.sort :as sh]))

;; Const values
(def cli-options
  [["-s"  "--sort SORT" "Sort option"
    :default :Demo
    :parse-fn #(get sh/sort-keymap (s/lower-case %))
    :validate [#(not
                 (contains? sh/sort-keymap (s/lower-case %)))
               "Valid sort options are 'LastName', 'Gender', 'DateOfBirth' or 'Demo' (runs all three on all three files)"]]
   ["-f" "--file FILE" "Files to load"
    :multi true
    :default ["./examples/comma-delim.txt"
              "./examples/pipe-delim.txt"
              "./examples/space-delim.txt"]
    :update-fn conj]
   ["-h" "--help" "Help"]])

;;fn mostly boilerplate lifted from the examples on the clojure.tools.cli github repo
(defn usage [options-summary]
  (->> ["gr-homework: This is my submission for a cli delimited file parser & REST api for a job interview."
        ""
        "Usage: gr-homework [options]"
        ""
        "Options:"
        options-summary
        ""]
       (s/join \newline)))

;;fn mostly boilerplate lifted from the examples on the clojure.tools.cli github repo

(defn error-msg [errors]
  (str "The following errors occurred while processing your request:\n\n"
       (s/join \newline errors)))

;;fn mostly boilerplate lifted from the examples on the clojure.tools.cli github repo

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
