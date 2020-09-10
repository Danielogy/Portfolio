let button = document.querySelector('input[type = "button"]');
let question = document.querySelector('input[type = "text"]');
let answer = document.getElementById('answer');
let answers = ["It is certain.",
               "It is decidedly so.",
               "Yes - Definitely.",
               "You may rely on it.",
               "As I see it, yes.",
               "Most Likely.",
               "Outlook good.",
               "Yes.",
               "Signs point to yes.",
               "Don't count on it.",
               "My reply is no.",
               "My sources say no.",
               "Outlook not so good.",
               "Very doubtful.",
               "Reply hazy, try again.",
               "Ask again later.",
               "Better not tell you now.",
               "Cannot predict now.",
               "Concentrate and ask again."];

question.addEventListener("keyup", event => {
    if(event.keyCode === 13){
      event.preventDefault();
      button.click();
    }
});

button.addEventListener("click", () => {
  if(question.value.length < 1){
    alert('Enter a question please.');
  }else{
      let num = Math.floor(Math.random() * Math.floor(answers.length));
      answer.innerHTML = answers[num];
      console.log(answer.innerHTML = answers[num]);
      question.value = "";
      question.placeholder = "";
  }
});
