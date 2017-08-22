(ns tic-tac-toe.player.human-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.player.human :refer :all]))


(describe "get-move"
          (around [it] (with-out-str (it)))
              (it "returns move"
                (should= 2 (with-in-str "2\n " 
                             (get-move [:x :_ :_
                                        :_ :_ :_
                                        :_ :_ :_ ] {:type :human} nil))))

            (it "disregards bad input" 
                (should= 2 (with-in-str "Z\n-1\nSEVEN\n0\n    2  \t " 
                             (get-move [:x :_ :_
                                        :_ :_ :_
                                        :_ :_ :_ ] {:type :human} nil)))))

