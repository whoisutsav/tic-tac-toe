(ns tic-tac-toe.web.runner-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.web.runner :refer :all]
            [tic-tac-toe.board :as board]))


(describe "map-request"
          (it "turns a request into a game object"
              (should= {
                         :board [:X :O :_
                                 :_ :_ :_
                                 :_ :_ :_ ] 
                         :current-player {:type :human-web :marker :Z }
                         :opponent-player {:type :hard-computer :marker :O}
                         :move 1
                        }
                       
                       (map-request {:form-params
                                     {"board" ["X" "O" "_"
                                              "_" "_" "_"
                                              "_" "_" "_"]
                                      "marker" "Z"
                                      "opponent" "hard-computer"
                                      "move" "1"
                                      }}))))

(describe "new-game"
          (it "returns a new-game"
              (should= {
                         :status "IN_PROGRESS"
                         :board [:_ :_ :_
                                 :_ :_ :_
                                 :_ :_ :_ ] 
                         :marker :X
                         :opponent :hard-computer
                         :winner nil }
                       (new-game))))

(describe "move"
          (it "takes turn"
              (should= { 
                           :status "WIN"
                           :winner :O
                           :marker :X
                           :opponent :hard-computer
                           :board [:X :O :X
                                   :_ :O :_
                                   :X :O :_ ]}
                       (move {:form-params { 
                                "board" [:X :O :X
                                         :_ :_ :_
                                         :_ :O :_] 
                                "marker" "X"
                                "opponent" "hard-computer" 
                                "move" "7"}})))
          ;(it "handles draw"
          ;    (should= { 
          ;                 :status "DRAW"
          ;                 :winner nil 
          ;                 :marker :X
          ;                 :opponent :hard-computer
          ;                 :board [:X :O :X
          ;                         :X :O :O
          ;                         :O :X :X ]}
          ;             (move {:form-params { 
          ;                      "board" [:X :O :X
          ;                               :X :O :O
          ;                               :O :X :_] 
          ;                      "marker" "X"
          ;                      "opponent" "hard-computer" 
          ;                      "move" "9"}})))
          )
