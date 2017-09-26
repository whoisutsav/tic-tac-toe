(ns tic-tac-toe.web.handler-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.web.handler :refer :all]
            [tic-tac-toe.board :as board]))


(describe "convert-to-game"
          (it "turns a request into a game object"
              (should= {
                         :board [:X :O :_
                                 :_ :_ :_
                                 :_ :_ :_ ] 
                         :current-player {:type :human-web :marker :Z }
                         :opponent-player {:type :hard-computer :marker :O}
                         :move 1
                        }
                       
                       (convert-to-game {:form-params
                                     {"board" ["X" "O" "_"
                                              "_" "_" "_"
                                              "_" "_" "_"]
                                      "marker" "Z"
                                      "opponent" "hard-computer"
                                      "move" "1"
                                      }}))))

(describe "handle-new"
          (it "returns a handle-new"
              (should= {
                         :status "IN_PROGRESS"
                         :board [:_ :_ :_
                                 :_ :_ :_
                                 :_ :_ :_ ] 
                         :marker :X
                         :opponent :hard-computer
                         :winner nil }
                       (:body (handle-new {:params {"opponent" "hard-computer"}})))))

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
                       (:body (handle {:form-params { 
                                "board" [:X :O :X
                                         :_ :_ :_
                                         :_ :O :_] 
                                "marker" "X"
                                "opponent" "hard-computer" 
                                "move" "7"}}))))
          ;(it "handles draw"
          ;    (should= { 
          ;                 :status "DRAW"
          ;                 :winner nil 
          ;                 :marker :X
          ;                 :opponent :hard-computer
          ;                 :board [:X :O :X
          ;                         :X :O :O
          ;                         :O :X :X ]}
          ;             (handle {:form-params { 
          ;                      "board" [:X :O :X
          ;                               :X :O :O
          ;                               :O :X :_] 
          ;                      "marker" "X"
          ;                      "opponent" "hard-computer" 
          ;                      "move" "9"}})))
          )
