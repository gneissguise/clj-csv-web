(ns gr-homework.handler.sort)

;; Comparators functions
(def compr-gender #(compare [(:Gender %1) (:LastName %1)]
                            [(:Gender %2) (:LastName %2)]))
(def compr-dob #(compare [(:DateOfBirth %1)]
                         [(:DateOfBirth %2)]))

(def compr-lastname #(compare [(:LastName %2)]
                              [(:LastName %1)]))


