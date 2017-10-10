(ns tic-tac-toe.console.human-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core.player :refer [get-move]]
            [tic-tac-toe.console.human :refer :all]))


(describe "get-move"
          (around [it] (with-out-str (it)))
              (it "returns move"
                (should= 2 (with-in-str "2\n " 
                             (get-move {:board [:x :_ :_
                                                :_ :_ :_
                                                :_ :_ :_ ] 
                                        :current-player {:type :human} 
                                        :opponent-player nil}))))

            (it "disregards bad input" 
                (should= 2 (with-in-str "Z\n-1\nSEVEN\n0\n    2  \t " 
                             (get-move {:board [:x :_ :_
                                               :_ :_ :_
                                               :_ :_ :_ ] 
                                       :current-player {:type :human} 
                                       :opponent-player nil})))))

