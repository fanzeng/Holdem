import { Card } from "../../card/Card"
export function Board({cardStage, communityCards}) {
  return <>
    <div className="board center-flex">
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' || cardStage === 'TURN' || cardStage === 'FLOP' ? <Card key="0" name={communityCards[0]}/> : <Card key="0" name="red_back"/> }
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' || cardStage === 'TURN' || cardStage === 'FLOP' ? <Card key="1" name={communityCards[1]}/> : <Card key="1" name="red_back"/> }
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' || cardStage === 'TURN' || cardStage === 'FLOP' ? <Card key="2" name={communityCards[2]}/> : <Card key="2" name="red_back"/> }
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' || cardStage === 'TURN' ? <Card key="3" name={communityCards[3]}/> : <Card key="3" name="red_back"/> }
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' ? <Card key="4" name={communityCards[4]}/> : <Card key="4" name="red_back"/> }
    </div>
  </>
}