class MealList {

    String[] mealList = {
    /*"balsamic-orzo-zucchini-stuffed-peppers", "beef-squash-red-lentil-chili", "bone-in-ribeye-with-horseradish-caper-butter-2", "broccoli-beef-bucatini", 
    "burnt-orange-ponzu-filet-mignon", "creamy-lemon-poppy-seed-fettuccine", "golden-chicken-breasts-1", "greek-kofta-beef-bowl", "ground-pork-lasagna-bolognese", 
    "mexican-spiced-chicken", "miso-honey-chicken","mushroom-dan-dan-noodles", "one-pan-puerto-ricanchicken-thighs", 
    "pork-burgers-with-homemade-sauerkraut", "pork-chops-with-homemade-maple-bacon-jam-1", "quick-shrimp-risotto", 
    "seared-pork-chopsin-mushroom-leek-pan-sauce-1", "seared-tofu-over-kimchi-rice-1", "spiced-butternut-squash-over-crispy-bulgur", 
    "sweet-chili-fig-pork-chopswith-roasted-nantes-carrots-1", "tomato-coconut-curry-chicken-1", "turmeric-tofu-pineapple-skewers-1",
    "branzino-with-caper-pine-nut-herb-salsa-1", "seared-coconut-haddock", "creamy-marsala-mushroom-chicken-thighs",
    "pork-peanut-noodle-stir-fry-1",  "sweet-potato-black-bean-quesadillas-1", "spiced-cod-puttanesca-1", "chicken-thighs-with-satay-sauce",
    "ground-beef-tomato-cremini-mushroom-soup", "seared-steakswith-maple-dijon-sauce", "chicken-salad-with-black-garlic-balsamic-vinaigrette",
    "pancetta-shimeji-mushroom-hot-pot-2", "bbq-tofu-ribs-1", "spiced-rubbed-pork-chops-with-citrus-roasted-pepper-salsa-1",
    "basque-style-bouillabaissewith-cod-2", "pork-rendangwith-coconut-turmeric-sauce-1", "sherry-chicken-thighs-1"
     "rice-noodlesin-coconut-curry-broth-2", "roasted-butternut-squash-dahl", 
    "spiced-rubbed-pork-chops-with-citrus-roasted-pepper-salsa-3", "creamy-chicken-stew", "dukkah-spiced-salmonwith-turmeric-tahini-sauce",
    "scallops-gnocchi-saint-jacques-1", "cheese-tortellini-with-honey-sundried-tomato-pesto-1", "pancetta-shimeji-mushroom-hot-pot-1",
    "tofu-cauliflower-tikka-masala-1", "moo-shu-pork-crepeswith-spiralized-sweet-potato-1", "curried-chicken-breastswith-carrots-3-ways-1", 
    "seared-steaks-4", "maple-sherry-sausage-vegetable-bake-1", "fresh-spinach-linguine-with-roasted-red-pepper-ajvar-sauce", 
    "seared-pork-chopswith-crispy-kohlrabi-bites", "argentinian-albondigaswith-chimichurri-2",
    "scandinavian-chicken-filletsover-basmati-rice-2", "veggie-burger-with-caramelized-onions-pickles-tomatoes-5", "roasted-cauliflowerwith-romesco-sauce-1",
    "one-pan-poached-haddock-shakshuka-2", "chicken-thighs-with-parmesan-potato-parsnip-fries-1", "mongolian-style-steak-1", "sicilian-style-porterhouse-steak-2",
    "confit-chicken-legsover-jewelled-pearl-couscous-1", "easy-beef-mushroom-bolognese-1", "charred-jalapeno-dijonpork-chops-1", "crispy-leek-orzotto", 
    "sweet-potato-zucchini-couscous-1", "lemon-herbed-chicken-breasts", "madras-coconut-salmon-curry-1", "argentinian-albondigaswith-chimichurri-1", 
    "one-pan-poached-haddock-shakshuka-1", "creamy-pesto-pasta-with-sundried-tomatoes-heirloom-zucchini-4", "hunan-pork-roast-with-peanut-broccoli-bok-choy-1" */
    };

    String[] test = {"pork-burgers-with-homemade-sauerkraut", "sweet-potato-black-bean-quesadillas-1"};

    public String list(int i){
        // return getURL(test[i]);
        return getURL(mealList[i]);
    }
    public String getURL(String extension) {
        StringBuilder URL = new StringBuilder();
        URL.append("https://www.makegoodfood.ca/en/recipe/");
        URL.append(extension);
        return URL.toString();
    }
    public int listSize() {
        // return test.length;
        return mealList.length;
    }
}