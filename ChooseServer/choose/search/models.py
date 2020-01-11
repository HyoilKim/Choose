from django.db import models

CATEGORY = [("생일", "생일"), ("효도", "효도"), ("출산, 돌", "출산, 돌"), ("명절", "명절"), ("입사", "입사"), ("퇴직", "퇴직"), ("입대", "입대"), ("전역", "전역"), ("연인", "연인"), 
("결혼", "결혼"), ("졸업", "졸업"), ("집들이", "집들이")]

# Create your models here.
class User(models.Model):
    email = models.EmailField(max_length=30, unique=True)
    password = models.CharField(max_length=15)
    birthday = models.DateField()

    def __str__(self):
        return self.email


class Item(models.Model):
    name = models.CharField(max_length=15)
    category = models.CharField(choices=CATEGORY, max_length=15)
    price = models.IntegerField()
    image = models.ImageField()

    def __str__(self):
        return '%s -%s-' % (self.name, self.category)

class Cart(models.Model):
    user = models.ForeignKey(User, related_name = 'cart', on_delete=models.CASCADE)
    item = models.ForeignKey(Item, related_name = 'cart', on_delete=models.CASCADE)
    count = models.IntegerField(default=1)

    def __str__(self):
        return '%s %d' % (self.user, self.item.id)

class Like(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    item = models.ForeignKey(Item, on_delete=models.CASCADE)

    def __str__(self):
        return '%s %s' % (self.user, self.item)
