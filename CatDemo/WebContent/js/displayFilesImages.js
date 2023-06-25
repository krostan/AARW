function previewMultiple(event) {
  const form = document.querySelector("#formFile");
  const images = document.getElementById("image");
  const number = images.files.length;

  form.innerHTML = "";
  
  for (i = 0; i < number; i++) {
    const file = event.target.files[i];
    const urls = URL.createObjectURL(file);
    form.innerHTML += '<img src="' + urls + '" >';
  }
  /*
  const file = event.target.files[0];
  const urls = URL.createObjectURL(file);
  form.innerHTML += '<img src="' + urls + '" >';*/
}
