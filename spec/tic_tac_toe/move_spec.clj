(ns tic-tac-toe.move-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.move :refer :all]))

(describe "get-cell"
          (it "prints error if move is invalid" 
              (should-contain "Move must be a number" (with-out-str (with-in-str "Z\n1" (get-move [nil nil nil nil])))))

          (it "keeps prompting until move is valid" (with-out-str (should= 2 (with-in-str "Z\n-1\n8\n0\n    2  \t " (get-move ["X" nil nil nil]))))))
