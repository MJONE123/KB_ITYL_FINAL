/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js}"],
  theme: {
    extend: {
      fontFamily: {
        sans: ['Pretendard', 'sans-serif'],
      },
      colors: {
        'kb-yellow-1' : "#F4BF42", 
        'kb-yellow-2':"#F6CE46",
        'kb-yellow-3':"#FAE6A2",
        'kb-yellow-4':"#FFFBE7",
        'kb-yellow-5':"#FBE9C8",
        'kb-yellow-6':"#E1CD9F",
        "kb-yellow-7":"#FFDB20",
        "kb-yellow-8":"#FFF8CE",
        "kb-yellow-9":"#FFEA7F",
        "kb-yellow-10":"#FBEDA4",
        "kb-yellow-11":"#FFCD4E",
        "kb-yellow-12":"#FFF4BD",
        "kb-yellow-13":"#FFFDF5",


        'kb-brown-1':"#535046",
        'kb-brown-2':"#5F584E",
        'kb-brown-3':"#897552",
        'kb-brown-4':"#563A3A",
        'kb-brown-5':"#635D38",
        'kb-brown-6':"#69571A",

        'kb-gray-1':"#85888B",     
        'kb-gray-2':"#B8B8BC",
        'kb-gray-3':"#BDBDBD",
        "kb-gray-4":"#A6A6A6",
        'nav-color':"#FFE89A",
        'font-color' :"#27272a",

        'kb-pink-1':"#FBB3B3",
        'kb-pink-2':"#FBDFFF",
        'kb-pink-3':"#FFC2AF",
        'kb-pink-4':"#FF40B3",
        'kb-pink-5':"#F8D8D8",
        'kb-pink-6':"#885757",
        "kb-pink-7":"#FF8484",
        'kb-pink-8':"#FF8863",

        'kb-blue-1':"#BBE4E7",
        'kb-blue-2':"#D6E0F8",
        "kb-blue-3":"#4E4ECD",
        "kb-blue-4":"#B7D4EF",
        "kb-blue-5":"#367D9B",
        "kb-blue-6":"#D7E9FA",
      

        'kb-green-1':"#DBF2BB",

        // 'kb-total-1':"#69571A",
        // 'kb-total-2':"#A19567",
        // 'kb-total-3':"#C5BD9F"

        'asset-yellow-1': "#F4BF42",
        'asset-yellow-2': "#FAE6A2",
        'asset-pink-1': "#FFAAAA",
        'asset-pink-2': "#F8D8D8",
        'asset-blue-1': "#8AC6FF",
        'asset-blue-2': "#CBE6FF",
        'asset-green-1' : '#B1EF5A',
        'asset-green-2':'#D3EFAC'

      },
      keyframes:{},
      screens:{
        laptop: "1537px"
      },
      transitionProperty: {
        'height' : 'height'
      },
    },
  },
  plugins: [],
};
