(ns gr-homework.handler.cli-test
 (:require [clojure.test
            :refer [deftest
                    testing
                    is
                    function?
                    use-fixtures
                    are]]
           [gr-homework.handler.cli :as cli]))

(deftest command-summary
  (testing "if the summary is formatted properly"
    (is (= (cli/usage "womp womp womp")
           "gr-homework: This is my submission for a cli delimited file parser & REST api for a job interview.\n\nUsage: gr-homework [options]\n\nOptions:\nwomp womp womp\n")))
  (testing "if the error message is formatted properly"
    (is (= (cli/error-msg ["pew" "pew" "pew"])
           "The following errors occurred while processing your request:\n\npew\npew\npew"))))
(deftest option-validation
  (testing "help options display"
    (is (= (and (cli/validate-args ["-h"]) (cli/validate-args ["--help"]))
           {:exit-message "gr-homework: This is my submission for a cli delimited file parser & REST api for a job interview.\n\nUsage: gr-homework [options]\n\nOptions:\n  -s, --sort SORT  :Demo                                                                                    Sort option\n  -f, --file FILE  [\"./examples/comma-delim.txt\" \"./examples/pipe-delim.txt\" \"./examples/space-delim.txt\"]  Files to load\n  -h, --help                                                                                                Help\n", :ok? true})))
  (testing "sorting options"
    (is (= (and (cli/validate-args ["-s" "Gender"]) (cli/validate-args ["--sort" "Gender"]))
           {:options {:sort :Gender, :file ["./examples/comma-delim.txt" "./examples/pipe-delim.txt" "./examples/space-delim.txt"]}}))
    (is (= (and (cli/validate-args ["-s" "DateOfBirth"]) (cli/validate-args ["--sort" "DateOfBirth"]))
           {:options {:sort :DateOfBirth, :file ["./examples/comma-delim.txt" "./examples/pipe-delim.txt" "./examples/space-delim.txt"]}}))
    (is (= (and (cli/validate-args ["-s" "LastName"]) (cli/validate-args ["--sort" "LastName"]))
           {:options {:sort :LastName, :file ["./examples/comma-delim.txt" "./examples/pipe-delim.txt" "./examples/space-delim.txt"]}}))
    (is (= (cli/validate-args ["-s" "nothing"])
           {:exit-message "The following errors occurred while processing your request:\n\nFailed to validate \"-s nothing\": Valid sort options are 'LastName', 'Gender', 'DateOfBirth' or 'Demo' (runs all three on all three files)"})))
  (testing "bad args"
    (is (= (cli/validate-args ["-r 3p --test"])
           {:exit-message "The following errors occurred while processing your request:\n\nUnknown option: \"-r\"\nUnknown option: \"- \"\nUnknown option: \"-3\"\nUnknown option: \"-p\"\nUnknown option: \"- \"\nUnknown option: \"--\"\nUnknown option: \"--\"\nUnknown option: \"-t\"\nUnknown option: \"-e\"\nFailed to validate \"-s t\": Valid sort options are 'LastName', 'Gender', 'DateOfBirth' or 'Demo' (runs all three on all three files)"})))
  (testing "no args"
    (is (= (cli/validate-args [])
           {:options {:sort :Demo, :file ["./examples/comma-delim.txt" "./examples/pipe-delim.txt" "./examples/space-delim.txt"]}}))))
